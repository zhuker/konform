package konform

import Rx.Observable
import com.vg.audio.*
import org.jcodec.codecs.wav.WavHeader
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Float64Array
import org.khronos.webgl.Int32Array
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag
import org.w3c.files.File

@JsName("indexFromMonoSource")
fun indexFromMonoSource(
    mono: MonoSource,
    sampleRate: Int,
    onProgress: (Long) -> Unit = {}
): Observable<FastAudioIndex> {
    return mono.readMono(samplesPerBin * 10, 0).concatMap { sb ->
        val floats = sb.samples[0]
        val js = floats as Float32Array
        val sz = floats.size
        onProgress(sb.range.start)
        Observable.range(0, sz / samplesPerBin).map { ii ->
            val i = ii.toInt() * samplesPerBin
            val sub = js.subarray(i, i + samplesPerBin)
            val sr = SampleRange(sb.range.start + i, sb.range.start + i + sub.length - 1)
            makeIndex((sub as FloatArray), sr, sampleRate)
        }
    }.toArray().map { it -> FastAudioIndex(it.toList(), samplesPerBin) }
}

private fun indexFromFile(p: Pair<File, WavHeader>): Observable<Array<IndexEntry>> {
    val file = p.first
    val wavHeader = p.second
    console.log("indexFromFile", file.name)
    return wavHeader.readSamples(file, samplesPerBin).map { sb ->
        makeIndex(sb.samples[0], sb.range, wavHeader.getSampleRate())
    }.toArray()
}

@JsName("indexToBlob")
fun indexToBlob(index: FastAudioIndex): Blob {
    val transferable = index.toTransferable()
    val header = intArrayOf(0x42424242, 3, transferable.bins, transferable.samplesPerBin, transferable.entries.size).buffer
    val body = transferable.entries.flatMap { it ->
        listOf(
            doubleArrayOf(it.sampleRangeStart, it.sampleRangeEnd).buffer,
            it.highScores.buffer,
            it.recordPoints.buffer
        )
    }.toTypedArray()
    val blob = Blob(arrayOf(header, *body), BlobPropertyBag("application/octet-stream"));
    return blob
}


private val DoubleArray.buffer: ArrayBuffer
    get() = (this as Float64Array).buffer

private val FloatArray.buffer: ArrayBuffer
    get() = (this as Float32Array).buffer

private val IntArray.buffer: ArrayBuffer
    get() = (this as Int32Array).buffer
