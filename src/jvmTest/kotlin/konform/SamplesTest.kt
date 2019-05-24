package konform

import com.vg.audio.SampleRange
import com.vg.audio.makeIndex
import js.lang.asByteBuffer
import js.lang.reverseBytes
import js.lang.toByteArray
import js.lang.toFloatArray
import js.nio.ByteBuffer
import org.jcodec.codecs.wav.WavHeader
import org.junit.Assert
import org.junit.Test
import toFloatSamples16
import toFloatSamples24
import java.io.File
import java.nio.channels.FileChannel.MapMode.READ_ONLY

class SamplesTest {
    @Test
    fun testToFloatSamples16() {
        val floats = File("testdata/signal").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val gimar = File("testdata/gimar.wav")
        val wavData = wavData(gimar).toByteArray()
        val samples = FloatArray(4096)
        toFloatSamples16(wavData, samples)
        Assert.assertArrayEquals("samples", floats, samples, 0.000001f)
    }

    private fun wavData(f: File): java.nio.ByteBuffer {
        val channel = f.inputStream().channel
        val map = channel.map(READ_ONLY, 0, f.length())
        val toByteArray1 = (map.limit(1024) as java.nio.ByteBuffer).slice().toByteArray()
        val wavHeader =
            WavHeader.readChannel(ByteBufferSeekableByteChannel.readFromByteBuffer(ByteBuffer.wrap(toByteArray1)))!!
        val dataOffset = wavHeader.dataOffset
        val sz = wavHeader.dataSize.toInt()
        map.position(dataOffset).limit(dataOffset + sz)
        val slice = map.slice()
        return slice;
    }
    //4337664

    @Test
    fun moneyb() {
        val floats = File("/tmp/floats24to16tofloat").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val floats24 = File("/tmp/floats24tofloat").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val wavData =
            wavData(File("/Users/zhukov/clients/sony/moneyball_2011_hd_16x9_178_2398_eng_OA9810_JPEG2000_a0.wav"))
        val toByteArray =
            (wavData.position(4337664 * 3).limit((4337664 + 4096) * 3) as java.nio.ByteBuffer).toByteArray()
        val samples = FloatArray(4096)
        toFloatSamples24(toByteArray, samples)
        Assert.assertArrayEquals(floats, samples, 0.0001f);
        Assert.assertArrayEquals(floats24, samples, 0.0001f);
        val makeIndex = makeIndex(samples, SampleRange(0, 4095), 48000)
        println(makeIndex)
    }

    @Test
    fun gimar24() {
        val floats = File("/tmp/floats24to16tofloat").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val floats24 = File("/tmp/floats24tofloat").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val makeIndex1 = makeIndex(floats, SampleRange(0, 4095), 48000)
        println(makeIndex1)
        val makeIndex2 = makeIndex(floats24, SampleRange(0, 4095), 48000)
        println(makeIndex2)

    }

    @Test
    fun gimar4337664() {
        val floats = File("/tmp/4337664").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val makeIndex2 = makeIndex(floats, SampleRange(0, 4095), 48000)
        println(makeIndex2)

    }
}