package com.vg.audio

import jdk.Math
import jdk.highestOneBit
import jdk.numberOfTrailingZeros
import js.nio.IntBuffer
import kotlin.js.JsName
import kotlin.math.abs
import kotlin.math.min

class FastAudioIndex {

    private val intBuf: IntBuffer
    val samplesPerBin: Int
    val bins: Int
    val indexEntries: List<IndexEntry>

    val sampleCount: Long
        get() = (intBuf.capacity() / bins * samplesPerBin.toLong())

    constructor(index: List<IndexEntry>, samplesPerBin: Int) {
        this.indexEntries = index
        this.samplesPerBin = samplesPerBin
        bins = index[0].recordPoints.size
        intBuf = allocIntBuf(bins * index.size)
        for (indexEntry in index) {
            intBuf.put(indexEntry.recordPoints)
        }
        intBuf.clear()
    }

    internal constructor(index: List<IndexEntry>, intBuf: IntBuffer, samplesPerBin: Int, bins: Int) {
        this.indexEntries = index
        this.intBuf = intBuf
        this.samplesPerBin = samplesPerBin
        this.bins = bins
    }

    private fun allocIntBuf(sz: Int) = IntBuffer.allocate(sz)

    fun nearestMatch(o: FastAudioIndex): Long {
        assertEquals(samplesPerBin, o.samplesPerBin)
        assertEquals(bins, o.bins)

        intBuf.clear()
        o.intBuf.clear()
        val minsadstep = IntByReference(0)
        minusad32(intBuf, o.intBuf, intBuf.remaining(), o.intBuf.remaining(), bins, minsadstep)
        val minsadbin = minsadstep.value
        return (minsadbin * samplesPerBin).toLong()
    }


    fun subIndex(startSample: Long, endSample: Long): FastAudioIndex {
        val startBin = (startSample / samplesPerBin).toInt()
        val endBin = (min(sampleCount - 1, endSample) / samplesPerBin).toInt()
        intBuf.clear()
        intBuf.position(startBin * bins)
        intBuf.limit((endBin + 1) * bins)
        val slice = intBuf.slice()
        val subList = indexEntries.subList(startBin, endBin + 1)
        return FastAudioIndex(subList, slice, samplesPerBin, bins)
    }

    fun toTransferable(): TransferableIndex {
        assertEquals(0, intBuf.arrayOffset())
        return TransferableIndex(indexEntries.map {
            TransferableIndexEntry(
                it.sampleRange.start.toDouble(),
                it.sampleRange.end.toDouble(),
                it.highScores,
                it.recordPoints
            )
        }.toTypedArray(), samplesPerBin, bins, intBuf.array())
    }
}

class TransferableIndexEntry(
    val sampleRangeStart: Double,
    val sampleRangeEnd: Double,
    val highScores: FloatArray,
    val recordPoints: IntArray
)

class TransferableIndex(
    val entries: Array<TransferableIndexEntry>,
    val samplesPerBin: Int,
    val bins: Int,
    val intBuf: IntArray
)

@JsName("indexFromTransferable")
fun indexFromTransferable(tidx: TransferableIndex): FastAudioIndex {
    return FastAudioIndex(tidx.entries.map {
        IndexEntry(
            SampleRange(
                it.sampleRangeStart.toLong(),
                it.sampleRangeEnd.toLong()
            ), it.highScores, it.recordPoints
        )
    }, IntBuffer.wrap(tidx.intBuf), tidx.samplesPerBin, tidx.bins)
}

fun assertEquals(a: Int, b: Int) {
    if (a != b) throw IllegalStateException("$a != $b")
}

class IntByReference(var value: Int)

fun minusad32(
    a1: IntBuffer,
    a2: IntBuffer,
    a1size: Int = a1.remaining(),
    a2size: Int = a2.remaining(),
    step: Int,
    minsadstep: IntByReference
): Int {
    var minsad = 0x7fffffff
    var minsadbin = 0
    val steps = (a1size - a2size) / step
    for (i in 0 until steps) {
        val sad = usad32(a1, a2, a2size)
        if (sad < minsad) {
            minsad = sad
            minsadbin = i
        }
        a1.position(a1.position() + step)
    }
    minsadstep.value = minsadbin
    return minsad
}

fun usad32(a1: IntBuffer, a2: IntBuffer, a2size: Int): Int {
    return if (a1.hasArray()) {
        var sad = 0
        val ia1 = a1.array()
        val ia2 = a2.array()
        val r1 = a1.remaining()
        val r2 = a2.remaining()
        val o1 = a1.arrayOffset() + a1.position()
        val o2 = a2.arrayOffset() + a2.position()
        val sz = Math.min(r1, r2)
        for (i in 0 until sz) {
            val a = ia1[o1 + i]
            val b = ia2[o2 + i]
            sad += abs(a - b)
        }
        sad
    } else {
        a1.mark()
        a2.mark()
        var sad = 0
        while (a1.hasRemaining() && a2.hasRemaining()) {
            val a = a1.get()
            val b = a2.get()
            sad += abs(a - b)
        }
        a1.reset()
        a2.reset()
        sad
    }
}

@JsName("matchOffsets")
fun matchOffsets(index1: FastAudioIndex, index2: FastAudioIndex): LongArray {
    val indexEntries = trimSilence(index2.indexEntries)
    val ranges = ArrayList<SampleRange>()
    val size = indexEntries.size

    addNonNull(ranges, nonSilenceRange(indexEntries, 0, size * 33 / 100))
    addNonNull(ranges, nonSilenceRange(indexEntries, size * 33 / 100, size * 66 / 100))
    addNonNull(ranges, nonSilenceRange(indexEntries, size * 66 / 100, size))

    if (ranges.size < 2) {
        return LongArray(1)
    }
    val matches = ranges.map { sampleRange ->
        index1.nearestMatch(index2.subIndex(sampleRange.start, sampleRange.end)) - sampleRange.start
    }

    return matches.toLongArray()
}

private fun addNonNull(ranges: MutableList<SampleRange>, nonSilenceRange: SampleRange?) {
    if (nonSilenceRange == null) return
    val hmsms = hmsms(nonSilenceRange.duration() / 48)
    val hmsms2 = hmsms(nonSilenceRange.start / 48)
    val hmsms3 = hmsms(nonSilenceRange.end / 48)
    println("non silence range: $hmsms $hmsms2 $hmsms3")
    ranges.add(nonSilenceRange)
}

private val zeroToNine = arrayOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09")

fun hmsms(_msec: Long): String {
    val msec = abs(_msec)
    val sec = msec / 1000
    val ms = (msec % 1000).toInt()
    val s = (sec % 60).toInt()
    val m = (sec % 3600 / 60).toInt()
    val h = (sec / 3600).toInt()

    var sb = StringBuilder(12)
    if (msec < 0) {
        sb.append("-")
    }
    sb = if (h in 0..9) sb.append(zeroToNine[h]) else sb.append(h)
    sb.append(":")
    sb = if (m in 0..9) sb.append(zeroToNine[m]) else sb.append(m)
    sb.append(":")
    sb = if (s in 0..9) sb.append(zeroToNine[s]) else sb.append(s)
    sb.append(".")
    when {
        ms >= 100 -> sb.append(ms)
        ms >= 10 -> sb.append('0').append(ms)
        else -> sb.append("00").append(ms)
    }
    return sb.toString()

}

private fun nonSilenceRange(indexEntries: List<IndexEntry>, start: Int, end: Int): SampleRange? {
    val ranges = mutableListOf<SampleRange>()
    for (i in start until end) {
        val indexEntry = indexEntries[i]
        if (!indexEntry.isSilence()) {
            val sampleRange = SampleRange(indexEntry.sampleRange.start, indexEntry.sampleRange.end)
            val last = if (ranges.isEmpty()) null else ranges.last()
            if (last != null) {
                if (last.end == sampleRange.start - 1) {
                    ranges[ranges.size - 1] = SampleRange(last.start, sampleRange.end)
                } else {
                    ranges.add(sampleRange)
                }
                if (last.duration() > 150 * 48000) {
                    return last
                }
            } else {
                ranges.add(sampleRange)
            }
        }
    }
    ranges.sortBy { it.duration() }
    return if (ranges.isEmpty()) null else ranges.last()
}

@JsName("bestOffset")
fun bestOffset(matchOffsets: LongArray): Long {
    if (matchOffsets.size == 2 && matchOffsets[0] == matchOffsets[1]) {
        println("offsets: ${toNiceString(matchOffsets)}")
        return matchOffsets[0]
    }

    val distinct = histo(matchOffsets)
    println("offsets: ${toNiceString(matchOffsets)} $distinct")
    return if (distinct.size <= 2) {
        return distinct.last().first
    } else {
        val min = matchOffsets.min() ?: -100500000L
        val max = matchOffsets.max() ?: 100500000L
        val hack = if (max - min <= 48000) {
            matchOffsets.average().toLong()
        } else {
            0
        }
        println("hack offset: ${toNiceString(hack)}")
        hack
    }
}


private fun toNiceString(matchOffsets: LongArray) = matchOffsets.joinToString(" ") { toNiceString(it) }

private fun toNiceString(offset: Long) = "${hmsms(offset / 48)} ($offset)"


private fun histo(matchOffsets: LongArray): List<Pair<Long, Int>> {
    return matchOffsets.groupBy { it }.map { (it.key to it.value.size) }.sortedBy { it.second }
}

const val samplesPerBin = 4096
const val cutoffFreq = 4096
const val bins = 8

private val fft = JTransformsFFT(samplesPerBin)

fun makeIndex(signal: FloatArray, sampleRange: SampleRange, sampleRate: Int): IndexEntry {
//    val fpp = 1f / signal.size * sampleRate

    val highestOneBit = (cutoffFreq / bins).highestOneBit().numberOfTrailingZeros()

    val highscores = FloatArray(bins + 1)
    val recordPoints = IntArray(bins + 1)
    val spectrum = fft.spectrum(signal)

    val ii = 5000 * signal.size / sampleRate

    for (i in 0 until ii) {
        val mag = spectrum[i]
        val freq = (i * sampleRate) shr 12 // signal.size
        var bin = freq shr highestOneBit
        bin = Math.min(bins, bin)
        if (mag > highscores[bin]) {
            highscores[bin] = mag
            recordPoints[bin] = freq
        }
    }
    val idx = IndexEntry(sampleRange, highscores, recordPoints)
    return idx
}