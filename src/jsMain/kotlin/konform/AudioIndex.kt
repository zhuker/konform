package konform

import Rx.Observable
import com.vg.audio.FastAudioIndex
import com.vg.audio.IndexEntry
import com.vg.audio.JTransformsFFT
import com.vg.audio.SampleRange
import jdk.Math
import jdk.highestOneBit
import jdk.numberOfTrailingZeros
import org.jcodec.codecs.wav.WavHeader
import org.khronos.webgl.Float32Array
import org.w3c.files.File


const val samplesPerBin = 4096
const val cutoffFreq = 4096
const val bins = 8

private val fft = JTransformsFFT(samplesPerBin)

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


fun makeIndex(signal: FloatArray, sampleRange: SampleRange, sampleRate: Int): IndexEntry {
    val fpp = 1f / signal.size * sampleRate

    val highestOneBit = (cutoffFreq / bins).highestOneBit().numberOfTrailingZeros()

    val highscores = FloatArray(bins + 1)
    val recordPoints = IntArray(bins + 1)
    val spectrum = fft.spectrum(signal)

    for (i in spectrum.indices) {
        val mag = spectrum[i]
        val freq = (fpp * i).toInt()
        if (freq < 5000) {
            var bin = freq shr highestOneBit
            bin = Math.min(bins, bin)
            if (mag > highscores[bin]) {
                highscores[bin] = mag
                recordPoints[bin] = freq
            }
        }
    }
    val idx = IndexEntry(sampleRange, highscores, recordPoints)
    return idx
}
