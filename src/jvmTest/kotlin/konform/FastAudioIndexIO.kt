package konform

import com.vg.audio.FastAudioIndex
import com.vg.audio.IndexEntry
import com.vg.audio.SampleRange
import js.lang.readDoubleLE
import js.lang.readFloatLE
import js.lang.readIntLE
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.util.*

private const val magicNumber = 0x42424242
private const val expectedVersion = 2
private const val expectedVersion3 = 3

fun readv2(input: File): FastAudioIndex {

    val data = DataInputStream(input.inputStream().buffered())
    val readInt = data.readInt()
    if (magicNumber != readInt) {
        throw IllegalStateException("wrong cache file format")
    }
    val version = data.readInt()
    if (expectedVersion != version) {
        throw IllegalStateException("unsupported cache file version $version")
    }

    val bins = data.readInt()
    val samplesPerBin = data.readInt()
    val entriesCount = data.readInt()
    val entries = ArrayList<IndexEntry>()
    for (i in 0 until entriesCount) {
        val start = data.readLong()
        val end = data.readLong()
        val range = SampleRange(start, end)
        val highscores = FloatArray(bins)
        val recordPoints = IntArray(bins)
        for (j in 0 until bins) {
            highscores[j] = data.readFloat()
        }
        for (j in 0 until bins) {
            recordPoints[j] = data.readInt()
        }
        val e = IndexEntry(range, highscores, recordPoints)
        entries.add(e)
    }
    data.close()
    return FastAudioIndex(entries, samplesPerBin)
}

fun writev2(index: FastAudioIndex, output: File) {
    val out = DataOutputStream(output.outputStream().buffered())
    out.writeInt(magicNumber)
    out.writeInt(expectedVersion)
    out.writeInt(index.bins)
    out.writeInt(index.samplesPerBin)
    val indexEntries = index.indexEntries
    out.writeInt(indexEntries.size)
    for (e in indexEntries) {
        val sampleRange = e.sampleRange
        out.writeLong(sampleRange.start)
        out.writeLong(sampleRange.end)
        for (f in e.highScores) {
            out.writeFloat(f)
        }
        for (i in e.recordPoints) {
            out.writeInt(i)
        }
    }
    out.close()
}


fun readv3(input: File): FastAudioIndex {

    val data = DataInputStream(input.inputStream().buffered())
    val readInt = data.readInt()
    if (magicNumber != readInt) {
        throw IllegalStateException("wrong cache file format")
    }
    val version = data.readIntLE()
    if (expectedVersion3 != version) {
        throw IllegalStateException("unsupported cache file version $version")
    }

    val bins = data.readIntLE()
    val samplesPerBin = data.readIntLE()
    val entriesCount = data.readIntLE()
    val entries = ArrayList<IndexEntry>()
    for (i in 0 until entriesCount) {
        val start = data.readDoubleLE()
        val end = data.readDoubleLE()
        val range = SampleRange(start.toLong(), end.toLong())
        val highscores = FloatArray(bins)
        val recordPoints = IntArray(bins)
        for (j in 0 until bins) {
            highscores[j] = data.readFloatLE()
        }
        for (j in 0 until bins) {
            recordPoints[j] = data.readIntLE()
        }
        val e = IndexEntry(range, highscores, recordPoints)
        entries.add(e)
    }
    data.close()
    return FastAudioIndex(entries, samplesPerBin)
}