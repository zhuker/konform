package js.lang

import java.io.DataInputStream
import java.nio.ByteBuffer
import java.nio.FloatBuffer

fun ByteBuffer.toByteArray(): ByteArray {
    val b = ByteArray(remaining())
    mark()
    get(b)
    reset()
    return b
}


fun FloatBuffer.toFloatArray(): FloatArray {
    val b = FloatArray(remaining())
    mark()
    get(b)
    reset()
    return b
}

fun ByteArray.asByteBuffer() = ByteBuffer.wrap(this)

fun DataInputStream.readIntLE(): Int {
    return Int.reverseBytes(readInt())
}

fun DataInputStream.readDoubleLE(): Double {
    val readLong = readLong()
    val reverseBytes = Long.reverseBytes(readLong)
    return java.lang.Double.longBitsToDouble(reverseBytes)
}

fun DataInputStream.readFloatLE(): Float {
    val v = readIntLE()
    return java.lang.Float.intBitsToFloat(v)
}
