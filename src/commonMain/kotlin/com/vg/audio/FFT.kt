package com.vg.audio

interface FFT {

    fun spectrum(signal: FloatArray): FloatArray

}