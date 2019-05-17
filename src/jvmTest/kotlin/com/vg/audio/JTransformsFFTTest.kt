package com.vg.audio

import js.lang.asByteBuffer
import js.lang.toFloatArray
import org.junit.Assert
import org.junit.Test
import java.io.File

class JTransformsFFTTest {
    @Test
    fun testSpectrum() {
        val fft = JTransformsFFT(samplesPerBin)
        val signal = File("testdata/signal").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val expected = File("testdata/spectrum").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val spectrum = fft.spectrum(signal)
        Assert.assertArrayEquals(expected, spectrum, 0.0000001f);
    }
}

