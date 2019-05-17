import com.vg.audio.Int24
import jdk.and
import js.lang.shl

fun toFloatSamples16(bytes: ByteArray, samples: FloatArray): FloatArray {
    for (i in 0 until bytes.size step 2) {
        val lo = bytes[i].toInt() and 0xff
        val hi = (bytes[i + 1] shl 8)
        val sample = hi or lo
        val fsample = Int24.uint16ToFloat(sample)
        samples[i shr 1] = fsample
    }
    return samples
}

fun toFloatSamples16_32k(bytes: ByteArray, samples: FloatArray): FloatArray {
    for (i in 0 until bytes.size step 2) {
        val lo = bytes[i] and 0xff
        val hi = (bytes[i + 1] shl 8)
        val sample = hi or lo
        val fsample = sample.toShort().toFloat()
        samples[i shr 1] = fsample
    }
    return samples
}

fun toFloatSamples24_32k(bytes: ByteArray, samples: FloatArray): FloatArray {
    var samplenum = 0
    for (i in 0 until bytes.size step 3) {
        val lo = bytes[i] and 0xff
        val mi = (bytes[i + 1] shl 8) and 0xffff
        val hi = (bytes[i + 2] shl 16)
        val sample = hi or mi or lo
        val fsample = Int24.uint24ToFloat(sample) * 32767f
        samples[samplenum] = fsample
        samplenum++
    }
    return samples
}

fun toFloatSamples24(bytes: ByteArray, samples: FloatArray): FloatArray {
    var samplenum = 0
    for (i in 0 until bytes.size step 3) {
        val lo = bytes[i] and 0xff
        val mi = (bytes[i + 1] shl 8) and 0xffff
        val hi = (bytes[i + 2] shl 16)
        val sample = hi or mi or lo
        val fsample = Int24.uint24ToFloat(sample)
        samples[samplenum] = fsample
        samplenum++
    }
    return samples
}

fun toFloatSamples(bytes: ByteArray, bps: Int): FloatArray {
    return when (bps) {
        2 -> toFloatSamples16(bytes, FloatArray(bytes.size / bps))
        3 -> toFloatSamples24(bytes, FloatArray(bytes.size / bps))
        else -> throw IllegalStateException("cant handle bps == $bps")
    }
}

fun toFloatSamples32k(bytes: ByteArray, bps: Int): FloatArray {
    return when (bps) {
        2 -> toFloatSamples16_32k(bytes, FloatArray(bytes.size / bps))
        3 -> toFloatSamples24_32k(bytes, FloatArray(bytes.size / bps))
        else -> throw IllegalStateException("cant handle bps == $bps")
    }
}