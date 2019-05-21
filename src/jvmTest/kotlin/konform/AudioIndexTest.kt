package konform

import js.lang.toByteArray
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import java.nio.ByteBuffer
import java.nio.ByteOrder

class AudioIndexTest {

    @Test
    fun endianess() {
        val b1 = ByteBuffer.allocate(4)
        val b2 = ByteBuffer.allocate(4)
        b1.order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer().put(420000000f)
        b2.order(ByteOrder.BIG_ENDIAN).asFloatBuffer().put(420000000f)
        assertArrayEquals(byteArrayOf(136.toByte(), 69, 200.toByte(), 77), b1.toByteArray())
        assertArrayEquals(byteArrayOf(77, 200.toByte(), 69, 136.toByte()), b2.toByteArray())
    }

}
