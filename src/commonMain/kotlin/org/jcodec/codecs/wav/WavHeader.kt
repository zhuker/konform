package org.jcodec.codecs.wav

import org.jcodec.api.UnhandledStateException
import org.jcodec.common.AudioFormat
import org.jcodec.common.JCodecUtil2
import org.jcodec.common.io.IOUtils
import org.jcodec.common.io.NIOUtils
import org.jcodec.common.model.ChannelLabel

import js.io.File
import js.io.IOException
import js.nio.ByteBuffer
import js.nio.ByteOrder
import js.nio.channels.ReadableByteChannel
import js.nio.channels.WritableByteChannel
import js.util.ArrayList
import org.jcodec.common.model.ChannelLabel.*

/**
 * This class is part of JCodec ( www.jcodec.org ) This software is distributed
 * under FreeBSD License
 *
 * @author The JCodec project
 */
class WavHeader(var chunkId: String, var chunkSize: Int, var format: String, var fmt: FmtChunk, var dataOffset: Int, var dataSize: Long) {

    val channelLabels: Array<ChannelLabel>
        get() = if (fmt is FmtChunkExtended) {
            (fmt as FmtChunkExtended).labels
        } else {
            when (fmt.numChannels.toInt()) {
                1 -> arrayOf(MONO)
                2 -> arrayOf(STEREO_LEFT, STEREO_RIGHT)
                3 -> arrayOf(FRONT_LEFT, FRONT_RIGHT, REAR_CENTER)
                4 -> arrayOf(FRONT_LEFT, FRONT_RIGHT, REAR_LEFT, REAR_RIGHT)
                5 -> arrayOf(FRONT_LEFT, FRONT_RIGHT, CENTER, REAR_LEFT, REAR_RIGHT)
                6 -> arrayOf(FRONT_LEFT, FRONT_RIGHT, CENTER, LFE, REAR_LEFT, REAR_RIGHT)
                7 -> arrayOf(FRONT_LEFT, FRONT_RIGHT, CENTER, LFE, REAR_LEFT, REAR_RIGHT, REAR_CENTER)
                8 -> arrayOf(FRONT_LEFT, FRONT_RIGHT, CENTER, LFE, REAR_LEFT, REAR_RIGHT, REAR_LEFT, REAR_RIGHT)
                else -> {
                    Array(fmt.numChannels.toInt()) { MONO }
                }
            }
        }

    class FmtChunkExtended(other: FmtChunk, internal var cbSize: Short, internal var bitsPerCodedSample: Short, internal var channelLayout: Int, internal var guid: Int) : FmtChunk(other.audioFormat, other.numChannels, other.sampleRate, other.byteRate, other.blockAlign, other.bitsPerSample) {

        val labels: Array<ChannelLabel>
            get() {
                val labels = ArrayList<ChannelLabel>()
                for (i in mapping.indices) {
                    if (channelLayout and (1 shl i) != 0)
                        labels.add(mapping[i])
                }
                return labels.toTypedArray()
            }

        //        @Throws(IOException::class)
        override fun put(bb: ByteBuffer) {
            super.put(bb)
            val old = bb.order() as ByteOrder
            bb.order(ByteOrder.LITTLE_ENDIAN)
            bb.putShort(cbSize)
            bb.putShort(bitsPerCodedSample)
            bb.putInt(channelLayout)
            bb.putInt(guid)
            bb.order(old)
        }

        override fun size(): Int {
            return super.size() + 12
        }

        companion object {

            //            @Throws(IOException::class)
            fun read(bb: ByteBuffer): FmtChunk {
                val fmtChunk = get(bb)
                val old = bb.order() as ByteOrder
                try {
                    bb.order(ByteOrder.LITTLE_ENDIAN)
                    return FmtChunkExtended(
                        fmtChunk,
                        bb.getShort(),
                        bb.getShort(),
                        bb.getInt(),
                        bb.getInt()
                    )
                } finally {
                    bb.order(old)
                }
            }
        }
    }

    fun getSampleRate(): Int {
        return fmt.sampleRate
    }

    fun getSampleCount(): Long {
        return dataSize / getBytesPerSample()
    }

    fun getBytesPerSample(): Int {
        return fmt.bitsPerSample.toInt().ushr(3)
    }

    fun getBitsPerSample(): Int {
        return fmt.bitsPerSample.toInt()
    }

    open class FmtChunk(var audioFormat: Short, var numChannels: Short, var sampleRate: Int, var byteRate: Int, var blockAlign: Short,
                        var bitsPerSample: Short) {

        //        @Throws(IOException::class)
        open fun put(bb: ByteBuffer) {
            val old = bb.order() as ByteOrder
            bb.order(ByteOrder.LITTLE_ENDIAN)
            bb.putShort(audioFormat)
            bb.putShort(numChannels)
            bb.putInt(sampleRate)
            bb.putInt(byteRate)
            bb.putShort(blockAlign)
            bb.putShort(bitsPerSample)
            bb.order(old)
        }

        open fun size(): Int {
            return 16
        }

        companion object {

            //            @Throws(IOException::class)
            fun get(bb: ByteBuffer): FmtChunk {
                val old = bb.order() as ByteOrder
                try {
                    bb.order(ByteOrder.LITTLE_ENDIAN)
                    return FmtChunk(
                        bb.getShort(), bb.getShort(), bb.getInt(), bb.getInt(), bb.getShort(),
                        bb.getShort()
                    )
                } finally {
                    bb.order(old)
                }
            }
        }
    }

    //    @Throws(IOException::class)
    fun write(out: WritableByteChannel) {
        val bb = ByteBuffer.allocate(44)
        bb.order(ByteOrder.LITTLE_ENDIAN)

        val chunkSize: Long = if (dataSize <= 0xffffffffL) {
            dataSize + 36
        } else {
            40
        }

        bb.put(JCodecUtil2.asciiString("RIFF"))
        bb.putInt(chunkSize.toInt())
        bb.put(JCodecUtil2.asciiString("WAVE"))

        bb.put(JCodecUtil2.asciiString("fmt "))
        bb.putInt(fmt.size())
        fmt.put(bb)
        bb.put(JCodecUtil2.asciiString("data"))
        if (dataSize <= 0xffffffffL) {
            bb.putInt(dataSize.toInt())
        } else {
            bb.putInt(0)
        }
        bb.flip()
        out.write(bb)
    }

    fun getFormat(): AudioFormat {
        return AudioFormat(
            fmt.sampleRate,
            fmt.bitsPerSample.toInt(),
            fmt.numChannels.toInt(),
            true,
            false
        )
    }

    companion object {

        internal var mapping = arrayOf(FRONT_LEFT, FRONT_RIGHT, CENTER, LFE, REAR_LEFT, REAR_RIGHT, FRONT_CENTER_LEFT, FRONT_CENTER_RIGHT, REAR_CENTER, SIDE_LEFT, SIDE_RIGHT, CENTER, FRONT_LEFT, CENTER, FRONT_RIGHT, REAR_LEFT, REAR_CENTER, REAR_RIGHT, STEREO_LEFT, STEREO_RIGHT)
        val WAV_HEADER_SIZE = 44

        fun copyWithRate(header: WavHeader, rate: Int): WavHeader {
            val result = WavHeader(
                header.chunkId, header.chunkSize, header.format,
                copyFmt(header.fmt), header.dataOffset, header.dataSize
            )
            result.fmt.sampleRate = rate
            return result
        }

        fun copyWithChannels(header: WavHeader, channels: Int): WavHeader {
            val result = WavHeader(
                header.chunkId, header.chunkSize, header.format,
                copyFmt(header.fmt), header.dataOffset, header.dataSize
            )
            result.fmt.numChannels = channels.toShort()
            return result
        }

        private fun copyFmt(fmt: FmtChunk): FmtChunk {
            var fmt = fmt
            if (fmt is FmtChunkExtended) {
                val fmtext = fmt
                fmt = FmtChunkExtended(
                    fmtext,
                    fmtext.cbSize,
                    fmtext.bitsPerCodedSample,
                    fmtext.channelLayout,
                    fmtext.guid
                )
            } else {
                fmt = FmtChunk(
                    fmt.audioFormat, fmt.numChannels, fmt.sampleRate, fmt.byteRate, fmt.blockAlign,
                    fmt.bitsPerSample
                )
            }
            return fmt
        }

        /**
         * Creates wav header for the specified audio format
         *
         * @param format
         * @param samples
         */
//        fun createWavHeader(format: AudioFormat, samples: Int): WavHeader {
//            return WavHeader("RIFF", 40, "WAVE", FmtChunk(1.toShort(), format.getChannels() as Short, format.getSampleRate(),
//                    format.getSampleRate() * format.getChannels() * (format.getSampleSizeInBits() shr 3),
//                    (format.getChannels() * (format.getSampleSizeInBits() shr 3)) as Short,
//                    format.getSampleSizeInBits() as Short), 44, calcDataSize(format.getChannels(),
//                    format.getSampleSizeInBits() shr 3, samples.toLong()))
//        }

        fun stereo48k(): WavHeader {
            return stereo48kWithSamples(0)
        }

        fun stereo48kWithSamples(samples: Long): WavHeader {
            return WavHeader(
                "RIFF", 40, "WAVE", FmtChunk(
                    1.toShort(), 2.toShort(), 48000, 48000 * 2 * 16 / 8,
                    4.toShort(), 16.toShort()
                ), 44, calcDataSize(2, 2, samples)
            )
        }

        fun mono48k(samples: Long): WavHeader {
            return WavHeader(
                "RIFF", 40, "WAVE", FmtChunk(
                    1.toShort(), 1.toShort(), 48000, 48000 * 1 * 16 / 8,
                    2.toShort(), 16.toShort()
                ), 44, calcDataSize(1, 2, samples)
            )
        }

        fun emptyWavHeader(): WavHeader {
            return WavHeader(
                "RIFF",
                40,
                "WAVE",
                newFmtChunk(),
                44,
                0
            )
        }

        private fun newFmtChunk(): FmtChunk {
            return FmtChunk(1.toShort(), 0.toShort(), 0, 0, 0.toShort(), 0.toShort())
        }

        //        @Throws(IOException::class)
        fun read(file: File): WavHeader? {
            var `is`: ReadableByteChannel? = null
            try {
                `is` = NIOUtils.readableChannel(file)
                return readChannel(`is`!!)
            } finally {
                IOUtils.closeQuietly(`is`)
            }
        }

        //        @Throws(IOException::class)
        fun readChannel(_in: ReadableByteChannel): WavHeader? {
            val buf = ByteBuffer.allocate(128)
            buf.order(ByteOrder.LITTLE_ENDIAN)
            _in.read(buf)
            if (buf.remaining() > 0)
                throw IOException("Incomplete wav header found")
            buf.flip()

            val chunkId = NIOUtils.readString(buf, 4)
            val chunkSize = buf.getInt()
            val format = NIOUtils.readString(buf, 4)
            var fmt: FmtChunk? = null

            if ("RIFF" != chunkId || "WAVE" != format) {
                return null
            }
            var fourcc: String
            var size = 0
            do {
                fourcc = NIOUtils.readString(buf, 4)
                size = buf.getInt()
                if ("fmt " == fourcc && size >= 14 && size <= 1024 * 1024) {
                    when (size) {
                        16 -> fmt = FmtChunk.get(buf)
                        18 -> {
                            fmt = FmtChunk.get(buf)
                            NIOUtils.skip(buf, 2)
                        }
                        40 -> {
                            fmt = FmtChunkExtended.read(buf)
                            NIOUtils.skip(buf, 12)
                        }
                        28 -> fmt = FmtChunkExtended.read(buf)
                        else -> throw UnhandledStateException("Don't know how to handle fmt size: $size")
                    }
                } else if ("data" != fourcc) {
                    NIOUtils.skip(buf, size)
                }
            } while ("data" != fourcc)

            return WavHeader(chunkId, chunkSize, format, fmt!!, buf.position(), size.toLong())
        }

        //        @Throws(IOException::class)
        fun multiChannelWavFromFiles(vararg arguments: File): WavHeader {
            val headers = arrayOfNulls<WavHeader>(arguments.size)
            for (i in arguments.indices) {
                headers[i] = read(arguments[i])
            }
            return multiChannelWav(*(headers.filterNotNull().toTypedArray()))
        }

        /** Takes single channel wavs as input produces multi channel wav  */
        fun multiChannelWav(vararg arguments: WavHeader): WavHeader {
            val w = emptyWavHeader()
            var totalSize = 0
            for (i in arguments.indices) {
                val wavHeader = arguments[i]
                totalSize += wavHeader.dataSize.toInt()
            }
            w.dataSize = totalSize.toLong()
            val fmt = arguments[0].fmt
            val bitsPerSample = fmt.bitsPerSample.toInt()
            val bytesPerSample = bitsPerSample / 8
            val sampleRate = fmt.sampleRate
            w.fmt.bitsPerSample = bitsPerSample.toShort()
            w.fmt.blockAlign = (arguments.size * bytesPerSample).toShort()
            w.fmt.byteRate = arguments.size * bytesPerSample * sampleRate
            w.fmt.numChannels = arguments.size.toShort()
            w.fmt.sampleRate = sampleRate
            return w
        }

        fun calcDataSize(numChannels: Int, bytesPerSample: Int, samples: Long): Long {
            return samples * numChannels.toLong() * bytesPerSample.toLong()
        }

        fun create(af: AudioFormat, size: Int): WavHeader {
            val w = emptyWavHeader()
            w.dataSize = size.toLong()
            val fmt = newFmtChunk()
            val bitsPerSample = af.sampleSizeInBits
            val bytesPerSample = bitsPerSample / 8
            val sampleRate = af.sampleRate as Int
            w.fmt.bitsPerSample = bitsPerSample.toShort()
            w.fmt.blockAlign = af.getFrameSize().toShort()
            w.fmt.byteRate = af.getFrameRate() as Int * af.getFrameSize()
            w.fmt.numChannels = af.channels.toShort()
            w.fmt.sampleRate = af.sampleRate
            return w
        }
    }
}
