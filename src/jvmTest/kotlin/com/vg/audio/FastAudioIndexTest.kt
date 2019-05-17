package com.vg.audio

import js.lang.asByteBuffer
import js.lang.toFloatArray
import konform.readv2
import org.junit.Assert
import org.junit.Test
import java.io.File

class FastAudioIndexTest {
    @Test
    fun testMakeIndex() {
        val j = readv2(File("testdata/idx1")).indexEntries[0]
        val signal = File("testdata/signal").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val k = makeIndex(signal, SampleRange(0, 4095), 48000)
        Assert.assertEquals(j.sampleRange.start, k.sampleRange.start)
        Assert.assertEquals(j.sampleRange.end, k.sampleRange.end)
        Assert.assertEquals("rms", j.rms, k.rms, 0.00001f)
        Assert.assertArrayEquals("recordPoints ", j.recordPoints, k.recordPoints)
        Assert.assertArrayEquals("highScores ", j.highScores, k.highScores, 0.000001f)
    }
}