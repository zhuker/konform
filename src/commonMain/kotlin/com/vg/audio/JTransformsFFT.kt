package com.vg.audio

import edu.emory.mathcs.jtransforms.fft.FloatFFT_1D
import jdk.Math

class JTransformsFFT(signalSize: Int) : FFT {
    private val jfft: FloatFFT_1D = FloatFFT_1D(signalSize)
    private val spectrum: FloatArray = FloatArray(signalSize / 2)
    private val copy: FloatArray= FloatArray(signalSize)

    override fun spectrum(signal: FloatArray): FloatArray {
        signal.copyInto(copy)
        jfft.realForward(copy)
        for (i in spectrum.indices) {
            val reali = copy[i * 2]
            val imagi = copy[i * 2 + 1]
            spectrum[i] = Math.sqrt((reali * reali + imagi * imagi).toDouble()).toFloat()
        }
        return spectrum
    }

}
