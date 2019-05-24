package org.jcodec.common.io

import js.io.File
import js.nio.ByteBuffer
import js.nio.channels.ReadableByteChannel
import kotlin.math.min

object NIOUtils {
    fun readString(buffer: ByteBuffer, len: Int): String {
        return stringFromBytes(
            toArray(
                read(
                    buffer,
                    len
                )
            )
        )
    }

    private fun stringFromBytes(bytes: ByteArray): String {
        return String(bytes.map { it.toChar() }.toCharArray())
    }

    fun skip(buffer: ByteBuffer, count: Int): Int {
        val toSkip = min(buffer.remaining(), count)
        buffer.position(buffer.position() + toSkip)
        return toSkip
    }

    fun readableChannel(file: File): ReadableByteChannel? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun read(buffer: ByteBuffer, count: Int): ByteBuffer {
        val slice = buffer.duplicate()
        val limit = buffer.position() + count
        slice.limit(limit)
        buffer.position(limit)
        return slice
    }

    fun toArray(buffer: ByteBuffer): ByteArray {
        val result = ByteArray(buffer.remaining())
        buffer.duplicate().get(result)
        return result
    }

}