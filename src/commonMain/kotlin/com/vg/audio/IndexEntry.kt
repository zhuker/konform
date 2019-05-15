package com.vg.audio

import jdk.Math
import kotlin.math.sqrt

data class SampleRange(val start: Long, val end: Long) {
    override fun toString(): String = "$start..$end"
    fun duration(): Long = end - start + 1

    fun containsRange(range: SampleRange): Boolean {
        return containsSample(range.start) && containsSample(range.end)
    }

    fun overlapsRange(range: SampleRange): Boolean {
        return range.containsSample(start) || range.containsSample(end) || containsSample(range.start)
    }

    fun containsSample(sample: Long): Boolean = sample in start..end

}

class IndexEntry(val sampleRange: SampleRange, val highScores: FloatArray, val recordPoints: IntArray) {
    val rms = rms(highScores)
}

fun IndexEntry.isSilence(): Boolean {
    return rms < 1f
}

fun IndexEntry.isTone(): Boolean {
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    for (i in highScores.indices) {
        if (highScores[i] > 5f) {
            min = Math.min(min, recordPoints[i])
            max = Math.max(max, recordPoints[i])
        }
    }
    return min != Int.MAX_VALUE && min > 900 && max < 1100
}

fun trimSilence(indexEntries: List<IndexEntry>): List<IndexEntry> {
    return indexEntries.dropWhile { it.isSilence() || it.isTone() }.dropLastWhile { it.isSilence() || it.isTone() }
}

fun rms(array: FloatArray): Float {
    if (array.size == 0)
        return 0f
    var rms = 0f
    for (i in array.indices) {
        rms += array[i] * array[i]
    }
    rms = sqrt((rms / array.size).toDouble()).toFloat()
    return rms
}
