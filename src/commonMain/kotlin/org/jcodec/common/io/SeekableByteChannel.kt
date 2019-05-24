package org.jcodec.common.io

import js.io.Closeable
import js.io.IOException
import js.nio.channels.ByteChannel
import js.nio.channels.Channel
import js.nio.channels.ReadableByteChannel
import js.nio.channels.WritableByteChannel

/**
 * This class is part of JCodec ( www.jcodec.org ) This software is distributed
 * under FreeBSD License
 *
 * @author The JCodec project
 */
interface SeekableByteChannel : ByteChannel, Channel, Closeable, ReadableByteChannel, WritableByteChannel {
//    @Throws(IOException::class)
    fun position(): Long

//    @Throws(IOException::class)
    fun setPosition(newPosition: Long): SeekableByteChannel

//    @Throws(IOException::class)
    fun size(): Long

//    @Throws(IOException::class)
    fun truncate(size: Long): SeekableByteChannel
}
