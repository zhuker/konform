package js.lang

import js.nio.ByteOrder
import org.khronos.webgl.*

actual object Platform {
    actual fun byteorder(): ByteOrder {
        val arrayBuffer = ArrayBuffer(4)
        val int32Array = Int32Array(arrayBuffer)
        int32Array[0] = 0x01020304
        val int8Array = Int8Array(arrayBuffer)
        val b = int8Array[0].toInt()
        return when (b) {
            0x01 -> ByteOrder.BIG_ENDIAN
            0x04 -> ByteOrder.LITTLE_ENDIAN
            else -> throw IllegalStateException("unknown byteorder ${b.toString(16)}")
        }
    }
}