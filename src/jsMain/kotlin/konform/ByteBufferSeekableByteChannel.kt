package konform

import js.io.IOException
import js.nio.ByteBuffer
import org.jcodec.common.io.NIOUtils
import org.jcodec.common.io.SeekableByteChannel
import kotlin.math.max
import kotlin.math.min

/**
 * This class is part of JCodec ( www.jcodec.org ) This software is distributed
 * under FreeBSD License
 *
 * Implements a seekable byte channel that wraps a byte buffer
 *
 * @author The JCodec project
 */
class ByteBufferSeekableByteChannel(private val backing: ByteBuffer, private var contentLength: Int) : SeekableByteChannel {
    private var isOpen = true
    override fun isOpen(): Boolean {
        return isOpen
    }

    val contents: ByteBuffer
        get() {
            val contents = backing.duplicate()
            contents.position(0)
            contents.limit(contentLength)
            return contents
        }

    init {
        this.isOpen = true
    }

    //    @Override
//    @Throws(IOException::class)
    override fun close() {
        isOpen = false
    }

    //    @Override
//    @Throws(IOException::class)
    override fun read(dst: ByteBuffer): Int {
        if (!backing.hasRemaining() || contentLength <= 0) {
            return -1
        }
        var toRead = min(backing.remaining(), dst.remaining())
        toRead = min(toRead, contentLength)
        dst.put(NIOUtils.read(backing, toRead))
        contentLength = max(contentLength, backing.position())
        return toRead
    }

    //    @Override
//    @Throws(IOException::class)
    override fun write(src: ByteBuffer): Int {
        val toWrite = min(backing.remaining(), src.remaining())
        backing.put(NIOUtils.read(src, toWrite))
        contentLength = max(contentLength, backing.position())
        return toWrite
    }

    //    @Override
//    @Throws(IOException::class)
    override fun position(): Long {
        return backing.position().toLong()
    }

    //    @Override
//    @Throws(IOException::class)
    override fun setPosition(newPosition: Long): SeekableByteChannel {
        backing.position(newPosition.toInt())
        contentLength = max(contentLength, backing.position())
        return this
    }

    //    @Override
//    @Throws(IOException::class)
    override fun size(): Long {
        return contentLength.toLong()
    }

    //    @Override
//    @Throws(IOException::class)
    override fun truncate(size: Long): SeekableByteChannel {
        contentLength = size.toInt()
        return this
    }

    companion object {

        fun writeToByteBuffer(buf: ByteBuffer): ByteBufferSeekableByteChannel {
            return ByteBufferSeekableByteChannel(buf, 0)
        }

        fun readFromByteBuffer(buf: ByteBuffer): ByteBufferSeekableByteChannel {
            return ByteBufferSeekableByteChannel(buf, buf.remaining())
        }
    }
}
