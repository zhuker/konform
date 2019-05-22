package konform

import Rx.Observable
import com.vg.audio.SampleRange
import org.jcodec.codecs.wav.WavHeader
import org.w3c.files.Blob
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

class FloatWaveData(override val spp: Int, val minmax: FloatArray, override val dataSampleRange: SampleRange) :
    IWaveData {
    override val dataSamples: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    fun getDataSpp(): Long {
        var d = this.dataSampleRange.duration();
        var spp = this.dataSamples / d;
        return spp;
    }

    override fun getSampleOffset(sample: Long): Int {
        assertTrue("sample not within data range", dataSampleRange.containsSample(sample));
        return spp * (sample - this.dataSampleRange.start).toInt()
    }

    override fun getMinMax16(sampleOffset: Int, minmax: IntArray) {
        val off = sampleOffset * 2
        if (off >= 0 && off < this.minmax.size - 1) {
            minmax[0] = (this.minmax[off] * 32768).roundToInt()
            minmax[1] = (this.minmax[off + 1] * 32768).roundToInt()
        }
    }

    override fun getMinMax8(startidx: Int, endidx: Int): Int {
        val from = max(0, startidx * 2)
        val to = min(minmax.size, endidx * 2) - 1
        var min = 0f
        var max = 0f
        for (j in from until to step 2) {
            val down = minmax[j]
            val up = minmax[j + 1]
            min = min(down, min)
            max = max(up, max)
        }
        return (max(kotlin.math.abs(min), max) * 255f).roundToInt()
    }
}

typealias MinMax<T> = Pair<T, T>

val <T> MinMax<T>.min: T get() = this.first
val <T> MinMax<T>.max: T get() = this.second

@JsName("waveDataFromWav")
fun waveDataFromWav(file: Blob, wavHeader: WavHeader, samplesPerPixel: Int): Observable<IWaveData> {
    return wavHeader.readSamples(file, samplesPerPixel).map {
        val min = it.samples[0].min() ?: 0f
        val max = it.samples[0].max() ?: 0f
        MinMax(min, max)
    }.toArray().map { arr ->
        val minmax = arr.flatMap { listOf(it.min, it.max) }.toFloatArray()
        FloatWaveData(samplesPerPixel, minmax, SampleRange(0, wavHeader.getSampleCount() - 1))
    }
}