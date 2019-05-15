package konform

import Rx.Observable
import com.vg.audio.SampleRange

private val standardSamplesPerPixel = intArrayOf(256, 4096, 32768, 131072);

class WaveformSourceImpl(private val mono: MonoSource) : WaveformSource {
    private val cached: MutableMap<Int, Observable<IWaveData>> = mutableMapOf()

    override fun getWaveImage(
        sampleRange: SampleRange,
        datasamples: Int,
        onload: WaveDataOnLoad,
        onerror: (Any) -> Unit
    ) {
        val wantspp = (sampleRange.duration() / datasamples).toInt()
        val standardSpp = standardSamplesPerPixel.findLast { it <= wantspp }
        println("wantspp $wantspp standardspp $standardSpp")
        if (standardSpp != null) {
            //full index
            cached.getOrPut(standardSpp, { waveDataFromMonoSource(mono, standardSpp).shareReplay() })
                .subscribe({ ok -> onload(ok) }, { err -> onerror(err) })
        } else {
            //precise wave
            TODO("not implemented")
        }
    }

}