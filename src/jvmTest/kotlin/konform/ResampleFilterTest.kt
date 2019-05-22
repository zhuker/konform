package konform

import org.junit.Assert
import kotlin.math.roundToLong
import kotlin.test.Test
import kotlin.test.assertEquals

class ResampleFilterTest {
    @Test
    fun test() {
        val len = 4800000L;
        val srcRate = 47952;
        val dstRate = 48000;
        val rLen = len * dstRate / srcRate
        assertEquals(4804804L, rLen)
    }

    @Test
    fun seek() {
        val rPos = 48048L;
        val srcRate = 47952;
        val dstRate = 48000;
        val pos = (rPos.toDouble() * srcRate / dstRate).roundToLong()
        assertEquals(48000L, pos)
    }

    @Test
    fun zoom() {
        val startSample = -48000
        val endSample = 48000
        val anchorSample = 24000
        val zoom = 2.0

        val toStart = anchorSample - startSample
        val toEnd = endSample - anchorSample

        val zoomedEnd = toEnd / zoom
        val newStart = anchorSample - toStart / zoom
        val newEnd = anchorSample + zoomedEnd
        Assert.assertEquals(-12000.0, newStart, 0.1)
        Assert.assertEquals(36000.0, newEnd, 0.1)
    }

}