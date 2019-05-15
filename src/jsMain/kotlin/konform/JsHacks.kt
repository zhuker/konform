package konform

import js.nio.ByteBuffer
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array

fun ArrayBuffer.asByteArray(): ByteArray {
    return Int8Array(this) as ByteArray
}

fun ArrayBuffer.asByteBuffer(): ByteBuffer {
    return ByteBuffer.wrap(Int8Array(this) as ByteArray)
}

fun ByteArray.asArrayBuffer(): ArrayBuffer {
    return (this as Int8Array).buffer
}
