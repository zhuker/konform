package konform

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


}