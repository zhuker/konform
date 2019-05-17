package konform

import Rx.Observable
import com.vg.audio.SampleRange
import org.jcodec.codecs.wav.WavHeader
import org.w3c.fetch.Response
import org.w3c.files.Blob
import toFloatSamples


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