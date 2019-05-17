package konform

import js.lang.asByteBuffer
import js.lang.toByteArray
import js.lang.toFloatArray
import org.junit.Assert
import org.junit.Test
import toFloatSamples16
import java.io.File

class SamplesTest {
    @Test
    fun testToFloatSamples16() {
        val floats = File("testdata/signal").readBytes().asByteBuffer().asFloatBuffer().toFloatArray()
        val gimar = File("testdata/gimar.wav")
        val wavData = wavData(gimar)
        val samples = FloatArray(4096)
        toFloatSamples16(wavData, samples)
        Assert.assertArrayEquals("samples", floats, samples, 0.000001f)
    }

    private fun wavData(f: File): ByteArray {
        val map = f.readBytes().asByteBuffer()
        val dataOffset = 0x28 + 4
        map.position(dataOffset).limit(dataOffset + 8192)
        val slice = map.slice()
        val toByteArray = slice.toByteArray()
        return toByteArray
    }
}