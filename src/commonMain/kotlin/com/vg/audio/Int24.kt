package com.vg.audio

object Int24 {
    const val MAX_VALUE = Int.MAX_VALUE.ushr(8) and 0xffffff
    const val MIN_VALUE = Int.MIN_VALUE.ushr(8) or -0x1000000

    inline fun toHexString(i24: Int): String = (i24 and 0xffffff).toString(16)

    inline fun fromInt24(i24: Int): Int = i24 shl 8 shr 8

    inline fun toSigned24(i: Int): Int = fromInt24(i)

    inline fun toUnsigned24(i: Int): Int = i and 0xffffff

    inline fun toUnsigned24(i: Long): Int = (i and 0xffffffL).toInt()

    inline fun floatToUint24(f: Float): Int {
        return toUnsigned24((f * Int24.MAX_VALUE).toInt())
    }

    inline fun floatToUint16(f: Float): Int {
        return (f * 32767f).toInt() and 0xffff
    }

    inline fun uint24ToFloat(uint24: Int): Float {
        return toSigned24(uint24) * r24
    }

    inline fun uint24ToFloatInline(uint24: Int): Float {
        return (uint24 shl 8 shr 8) * r24
    }

    inline fun uint16ToFloat(packed: Int): Float {
        return packed.toShort() * r
    }

    inline fun int16ToFloat(packed: Short): Float {
        return packed * r
    }

    const val r = 1f / 32767f
    const val r24 = 1f / Int24.MAX_VALUE

}
