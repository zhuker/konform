package konform

import Rx.Observable
import com.vg.audio.Int24
import com.vg.audio.SampleRange
import jdk.and
import js.lang.shl
import org.jcodec.codecs.wav.WavHeader
import org.w3c.fetch.Response
import org.w3c.files.Blob


@JsName("readSamples")
fun WavHeader.readSamples(file: Blob, samplesPerChunk: Int, startSample: Int = 0): Observable<SampleBuffer> {
    val bps = this.getBytesPerSample()
    val dataOffset = this.dataOffset + startSample * bps
    val bytesPerChunk = samplesPerChunk * bps
    return Observable.range(0, (this.dataSize / bytesPerChunk).toInt()).concatMap { chunk ->
        val c = chunk.toInt()
        val offset = dataOffset + c * bytesPerChunk
        val startSamples = startSample + samplesPerChunk.toLong() * c
        Observable.create<Blob> {
//            console.log("read", offset, offset + bytesPerChunk)
            val slice = file.slice(offset, offset + bytesPerChunk)
//            console.log("slice", offset, offset + bytesPerChunk, slice)
            it.onNext(slice)
            it.onCompleted()
        }.concatMap { slice ->
            Observable.fromPromise(Response(slice).arrayBuffer()).map {
//                console.log("read ok", offset, offset + bytesPerChunk, it.byteLength)
                val samples = it.byteLength / bps
                if (samples != samplesPerChunk) {
                    console.log("expected samples $samplesPerChunk got $samples")
                }
                val endSample = (startSamples + samples - 1)
                SampleRange(startSamples, endSample) to it
            }
        }
    }.map { p ->
        val sampleRange = p.first
        val arr = p.second
        if (arr.byteLength % bps != 0) {
            throw IllegalStateException("uneven sample count ${arr.byteLength} not divisible by $bps")
        }
        val bytes = arr.asByteArray()
        val samples = toFloatSamples(bytes, bps)
//        console.log("samples", sampleRange, samples)
        SampleBuffer(sampleRange, arrayOf(samples))
    }
}

fun toFloatSamples16(bytes: ByteArray, samples: FloatArray): FloatArray {
    for (i in 0 until bytes.size step 2) {
        val lo = bytes[i].toInt()
        val hi = (bytes[i + 1] shl 8)
        val sample = hi or lo
        val fsample = Int24.uint16ToFloat(sample)
        samples[i shr 1] = fsample
    }
    return samples
}

fun toFloatSamples16_32k(bytes: ByteArray, samples: FloatArray): FloatArray {
    for (i in 0 until bytes.size step 2) {
        val lo = bytes[i] and 0xff
        val hi = (bytes[i + 1] shl 8)
        val sample = hi or lo
        val fsample = sample.toShort().toFloat()
        samples[i shr 1] = fsample
    }
    return samples
}

fun toFloatSamples24_32k(bytes: ByteArray, samples: FloatArray): FloatArray {
    var samplenum = 0
    for (i in 0 until bytes.size step 3) {
        val lo = bytes[i] and 0xff
        val mi = (bytes[i + 1] shl 8) and 0xffff
        val hi = (bytes[i + 2] shl 16)
        val sample = hi or mi or lo
        val fsample = Int24.uint24ToFloat(sample) * 32767f
        samples[samplenum] = fsample
        samplenum++
    }
    return samples
}

fun toFloatSamples24(bytes: ByteArray, samples: FloatArray): FloatArray {
    var samplenum = 0
    for (i in 0 until bytes.size step 3) {
        val lo = bytes[i] and 0xff
        val mi = (bytes[i + 1] shl 8) and 0xffff
        val hi = (bytes[i + 2] shl 16)
        val sample = hi or mi or lo
        val fsample = Int24.uint24ToFloat(sample)
        samples[samplenum] = fsample
        samplenum++
    }
    return samples
}

fun toFloatSamples(bytes: ByteArray, bps: Int): FloatArray {
    return when (bps) {
        2 -> toFloatSamples16(bytes, FloatArray(bytes.size / bps))
        3 -> toFloatSamples24(bytes, FloatArray(bytes.size / bps))
        else -> throw IllegalStateException("cant handle bps == $bps")
    }
}

fun toFloatSamples32k(bytes: ByteArray, bps: Int): FloatArray {
    return when (bps) {
        2 -> toFloatSamples16_32k(bytes, FloatArray(bytes.size / bps))
        3 -> toFloatSamples24_32k(bytes, FloatArray(bytes.size / bps))
        else -> throw IllegalStateException("cant handle bps == $bps")
    }
}

@JsName("wavHeader")
fun wavHeader(file: Blob): Observable<WavHeader> {
    return Observable.fromPromise(Response(file.slice(0, 1024)).arrayBuffer()).map { header ->
        console.log("arrayBuffer", header)
        val wrap = header.asByteBuffer()
        val bbsbc = ByteBufferSeekableByteChannel.readFromByteBuffer(wrap)
        val wavHeader = WavHeader.readChannel(bbsbc)!!
        wavHeader
    }
}