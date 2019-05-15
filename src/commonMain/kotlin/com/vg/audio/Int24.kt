package com.vg.audio

object Int24 {
    var MAX_VALUE = Int.MAX_VALUE.ushr(8) and 0xffffff
    var MIN_VALUE = Int.MIN_VALUE.ushr(8) or -0x1000000

    fun toHexString(i24: Int): String = (i24 and 0xffffff).toString(16)

    fun fromInt24(i24: Int): Int = i24 shl 8 shr 8

    fun toSigned24(i: Int): Int = fromInt24(i)

    fun toUnsigned24(i: Int): Int = i and 0xffffff

    fun toUnsigned24(i: Long): Int = (i and 0xffffffL).toInt()

    fun floatToUint24(f: Float): Int {
        return toUnsigned24((f * Int24.MAX_VALUE).toInt())
    }

    fun floatToUint16(f: Float): Int {
        return (f * 32767f).toInt() and 0xffff
    }

    fun uint24ToFloat(uint24: Int): Float {
        return toSigned24(uint24) * r24
    }

    fun uint16ToFloat(packed: Int): Float {
        return packed.toShort() * r
    }

    fun int16ToFloat(packed: Short): Float {
        return packed * r
    }

    val r = 1f / 32767f
    val r24 = 1f / Int24.MAX_VALUE

}
