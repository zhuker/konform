package com.vg.audio

import js.lang.asByteBuffer
import js.lang.toFloatArray
import konform.readv2
import konform.readv3
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

    @Test
    fun moneyb() {
        val jf = File("/Volumes/BigfootAudioSync/cache/mo/moneyball_2011_hd_16x9_178_2398_eng_OA9810_JPEG2000.1.wg")
        val kf = File("/Users/zhukov/Downloads/moneyball_2011_hd_16x9_178_2398_eng_OA9810_JPEG2000_a0.wav_undefined.idx")
        val ji = readv2(jf)
        val ki = readv3(kf)
        ji.indexEntries.zip(ki.indexEntries).forEachIndexed { index, pair ->
            val j = pair.first
            val k = pair.second
            Assert.assertEquals(j.sampleRange.start, k.sampleRange.start)
            Assert.assertEquals(j.sampleRange.end, k.sampleRange.end)
//            Assert.assertEquals("rms at $index", j.rms, k.rms, 0.00001f)
            Assert.assertArrayEquals("recordPoints at $index", j.recordPoints, k.recordPoints)
//            Assert.assertArrayEquals("highScores at $index", j.highScores, k.highScores, 0.000001f)
        }
    }
}