package konform

import Rx.Observable
import com.vg.audio.SampleRange
import org.khronos.webgl.Float32Array

private val standardSamplesPerPixel = intArrayOf(256, 4096, 32768, 131072);

class WaveformSourceImpl(private val mono: MonoSource) : WaveformSource {
    private val cached: MutableMap<Int, Observable<FloatWaveData>> = mutableMapOf()

    override fun getWaveImage(
        sampleRange: SampleRange,
        datasamples: Int,
        onload: WaveDataOnLoad,
        onerror: (Any) -> Unit
    ) {
        val wantspp = (sampleRange.duration() / datasamples).toInt()
        val standardSpp = standardSamplesPerPixel.findLast { it <= wantspp }
        console.log("wantspp", wantspp, "standardspp", standardSpp)
        if (standardSpp != null) {
            //full index
            val spp256 = cached.getOrPut(256, {
                mono.readMono(131072, 0)
                    .split(256).minMax()
                    .map { FloatWaveData(standardSpp, it, SampleRange(0, mono.length - 1)) }
                    .shareReplay()
            })
            if (standardSpp == 256) {
                spp256
            } else {
                cached.getOrPut(standardSpp, { spp256.map { fwd -> downscale(standardSpp, fwd) } })
            }.subscribe({ ok ->
                console.log("ok", standardSpp)
                onload(ok)
            }, { err -> onerror(err) })
        } else {
            //precise wave
            TODO("not implemented")
        }
    }

    private fun downscale(spp: Int, fwd256: FloatWaveData): FloatWaveData {
        val step = (spp / 256) * 2
        val minmax = fwd256.minmax
        val f32 = minmax as Float32Array
        val result = FloatArray(minmax.size / step * 2)
        var j = 0
        for (i in minmax.indices step step) {
            val sub = f32.subarray(i, i + step)
            val min = (sub as FloatArray).min() ?: 0f
            val max = (sub as FloatArray).max() ?: 0f
            result[j++] = min
            result[j++] = max
        }

        return FloatWaveData(spp, result, fwd256.dataSampleRange)
    }
}

private fun Observable<FloatArray>.minMax(): Observable<FloatArray> {
    return map { samples ->
        val min = samples.min() ?: 0f
        val max = samples.max() ?: 0f
        MinMax(min, max)
    }.reduce({ prev, cur ->
        //kotlin.Array and javascript Array are the same thing so I blatantly exploit it here
        prev.asDynamic().push(cur.min)
        prev.asDynamic().push(cur.max)
        prev
    }, arrayOf<Float>()).map { it.toFloatArray() }
}

private fun Observable<SampleBuffer>.split(chunkSize: Int): Observable<FloatArray> {
    return concatMap { sb ->
        val floats = sb.samples[0]
        val js = floats as Float32Array
        val sz = floats.size

        Observable.range(0, sz / chunkSize).map { ii ->
            val i = ii.toInt() * chunkSize
            val sub = js.subarray(i, i + chunkSize)
            sub as FloatArray
        }
    }
}