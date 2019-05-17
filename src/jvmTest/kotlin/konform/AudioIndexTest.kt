package konform

import jdk.and
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
        for (i in 0..3) {
            val get = b1.get(i) and 0xff
            println(get)
        }
        for (i in 0..3) {
            val get = b2.get(i) and 0xff
            println(get)
        }
    }

}
