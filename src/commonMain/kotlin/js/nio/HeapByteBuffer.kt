/*
 * Copyright (c) 2000, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

// -- This file was mechanically generated: Do not edit! -- //

package js.nio

import js.lang.System


/**
 *
 * A read/write HeapByteBuffer.
 *
 *
 *
 *
 *
 *
 */

internal open class HeapByteBuffer
(override val hb: ByteArray,
 mrk: Int = -1,
 pos: Int = 0,
 lim: Int,
 cap: Int,
 off: Int
) : ByteBuffer(mrk, pos, lim, cap, hb, off) {


    override fun isDirect(): Boolean = false


    override var isReadOnly: Boolean = false

    // char


    override fun getChar(): Char = Bits.getChar(this, ix(nextGetIndex(2)), bigEndian)


    // short


    override fun getShort(): Short = Bits.getShort(this, ix(nextGetIndex(2)), bigEndian)


    // int


    override fun getInt(): Int = Bits.getInt(this, ix(nextGetIndex(4)), bigEndian)


    // long


    override fun getLong(): Long = Bits.getLong(this, ix(nextGetIndex(8)), bigEndian)


    // float


    override fun getFloat(): Float = Bits.getFloat(this, ix(nextGetIndex(4)), bigEndian)


    // double


    override fun getDouble(): Double = Bits.getDouble(this, ix(nextGetIndex(8)), bigEndian)

    // For speed these fields are actually declared in X-Buffer;
    // these declarations are here as documentation
    /*

    protected final byte[] hb;
    protected final int offset;

    */

    // package-private
    constructor(cap: Int, lim: Int) : this(ByteArray(cap), -1, 0, lim, cap, 0)

    // package-private
    constructor(buf: ByteArray, off: Int, len: Int) : this(buf, -1, 0, off + len, buf.size, off)

//    protected constructor(buf: ByteArray,
//                          mark: Int, pos: Int, lim: Int, cap: Int,
//                          off: Int) : super(mark, pos, lim, cap, buf, off) {
//        /*
//        hb = buf;
//        offset = off;
//        */
//
//
//    }

    override fun slice(): ByteBuffer {
        return HeapByteBuffer(hb,
                -1,
                0,
                this.remaining(),
                this.remaining(),
                this.position() + offset)
    }

    override fun duplicate(): ByteBuffer {
        return HeapByteBuffer(hb,
                this.markValue(),
                this.position(),
                this.limit(),
                this.capacity(),
                offset)
    }

    override fun asReadOnlyBuffer(): ByteBuffer {

        return HeapByteBufferR(hb,
                this.markValue(),
                this.position(),
                this.limit(),
                this.capacity(),
                offset)


    }


    protected fun ix(i: Int): Int {
        return i + offset
    }

    override fun get(): Byte {
        return hb[ix(nextGetIndex())]
    }

    override fun get(i: Int): Byte {
        return hb[ix(checkIndex(i))]
    }


    override fun get(dst: ByteArray, offset: Int, length: Int): ByteBuffer {
        checkBounds(offset, length, dst.size)
        if (length > remaining())
            throw BufferUnderflowException()
        System.arraycopy(hb, ix(position()), dst, offset, length)
        position(position() + length)
        return this
    }

    override fun put(x: Byte): ByteBuffer {

        hb[ix(nextPutIndex())] = x
        return this


    }

    override fun put(i: Int, x: Byte): ByteBuffer {

        hb[ix(checkIndex(i))] = x
        return this


    }

    override fun put(src: ByteArray, offset: Int, length: Int): ByteBuffer {

        checkBounds(offset, length, src.size)
        if (length > remaining())
            throw BufferOverflowException()
        System.arraycopy(src, offset, hb, ix(position()), length)
        position(position() + length)
        return this


    }

    override fun put(src: ByteBuffer): ByteBuffer {

        if (src is HeapByteBuffer) {
            if (src === this)
                throw IllegalArgumentException()
            val n = src.remaining()
            if (n > remaining())
                throw BufferOverflowException()
            System.arraycopy(src.hb, src.ix(src.position()),
                    hb, ix(position()), n)
            src.position(src.position() + n)
            position(position() + n)
        } else if (src.isDirect()) {
            val n = src.remaining()
            if (n > remaining())
                throw BufferOverflowException()
            src.get(hb, ix(position()), n)
            position(position() + n)
        } else {
            super.put(src)
        }
        return this


    }

    override fun compact(): ByteBuffer {
        System.arraycopy(hb, ix(position()), hb, ix(0), remaining())
        position(remaining())
        limit(capacity())
        discardMark()
        return this


    }


    override fun _get(i: Int): Byte {                          // package-private
        return hb[i]
    }

    override fun _put(i: Int, b: Byte) {                  // package-private

        hb[i] = b


    }

    override fun getChar(i: Int): Char {
        return Bits.getChar(this, ix(checkIndex(i, 2)), bigEndian)
    }


    override fun putChar(x: Char): ByteBuffer {

        Bits.putChar(this, ix(nextPutIndex(2)), x, bigEndian)
        return this


    }

    override fun putChar(i: Int, x: Char): ByteBuffer {

        Bits.putChar(this, ix(checkIndex(i, 2)), x, bigEndian)
        return this


    }

    override fun asCharBuffer(): CharBuffer {
        val size = this.remaining() shr 1
        val off = offset + position()
        return if (bigEndian)
            ByteBufferAsCharBufferB(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as CharBuffer
        else
            ByteBufferAsCharBufferL(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as CharBuffer
    }

    override fun getShort(i: Int): Short {
        return Bits.getShort(this, ix(checkIndex(i, 2)), bigEndian)
    }


    override fun putShort(x: Short): ByteBuffer {

        Bits.putShort(this, ix(nextPutIndex(2)), x, bigEndian)
        return this


    }

    override fun putShort(i: Int, x: Short): ByteBuffer {

        Bits.putShort(this, ix(checkIndex(i, 2)), x, bigEndian)
        return this


    }

    override fun asShortBuffer(): ShortBuffer {
        val size = this.remaining() shr 1
        val off = offset + position()
        return if (bigEndian)
            ByteBufferAsShortBufferB(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as ShortBuffer
        else
            ByteBufferAsShortBufferL(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as ShortBuffer
    }

    override fun getInt(i: Int): Int {
        return Bits.getInt(this, ix(checkIndex(i, 4)), bigEndian)
    }


    override fun putInt(x: Int): ByteBuffer {

        Bits.putInt(this, ix(nextPutIndex(4)), x, bigEndian)
        return this


    }

    override fun putInt(i: Int, x: Int): ByteBuffer {

        Bits.putInt(this, ix(checkIndex(i, 4)), x, bigEndian)
        return this


    }

    override fun asIntBuffer(): IntBuffer {
        val size = this.remaining() shr 2
        val off = offset + position()
        return if (bigEndian)
            ByteBufferAsIntBufferB(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as IntBuffer
        else
            ByteBufferAsIntBufferL(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as IntBuffer
    }

    override fun getLong(i: Int): Long {
        return Bits.getLong(this, ix(checkIndex(i, 8)), bigEndian)
    }


    override fun putLong(x: Long): ByteBuffer {

        Bits.putLong(this, ix(nextPutIndex(8)), x, bigEndian)
        return this


    }

    override fun putLong(i: Int, x: Long): ByteBuffer {

        Bits.putLong(this, ix(checkIndex(i, 8)), x, bigEndian)
        return this


    }

    override fun asLongBuffer(): LongBuffer {
        val size = this.remaining() shr 3
        val off = offset + position()
        return if (bigEndian)
            ByteBufferAsLongBufferB(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as LongBuffer
        else
            ByteBufferAsLongBufferL(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as LongBuffer
    }

    override fun getFloat(i: Int): Float {
        return Bits.getFloat(this, ix(checkIndex(i, 4)), bigEndian)
    }


    override fun putFloat(x: Float): ByteBuffer {

        Bits.putFloat(this, ix(nextPutIndex(4)), x, bigEndian)
        return this


    }

    override fun putFloat(i: Int, x: Float): ByteBuffer {

        Bits.putFloat(this, ix(checkIndex(i, 4)), x, bigEndian)
        return this


    }

    override fun asFloatBuffer(): FloatBuffer {
        val size = this.remaining() shr 2
        val off = offset + position()
        return if (bigEndian)
            ByteBufferAsFloatBufferB(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as FloatBuffer
        else
            ByteBufferAsFloatBufferL(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as FloatBuffer
    }

    override fun getDouble(i: Int): Double {
        return Bits.getDouble(this, ix(checkIndex(i, 8)), bigEndian)
    }


    override fun putDouble(x: Double): ByteBuffer {

        Bits.putDouble(this, ix(nextPutIndex(8)), x, bigEndian)
        return this


    }

    override fun putDouble(i: Int, x: Double): ByteBuffer {

        Bits.putDouble(this, ix(checkIndex(i, 8)), x, bigEndian)
        return this


    }

    override fun asDoubleBuffer(): DoubleBuffer {
        val size = this.remaining() shr 3
        val off = offset + position()
        return if (bigEndian)
            ByteBufferAsDoubleBufferB(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as DoubleBuffer
        else
            ByteBufferAsDoubleBufferL(this,
                    -1,
                    0,
                    size,
                    size,
                    off) as DoubleBuffer
    }


}

internal class HeapByteBufferR(hb: ByteArray,
                               mrk: Int,
                               pos: Int,
                               lim: Int,
                               cap: Int,
                               off: Int
) : HeapByteBuffer(hb, mrk, pos, lim, cap, off)

class ByteBufferAsDoubleBufferL internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsDoubleBufferB internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsFloatBufferL internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsFloatBufferB internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsLongBufferL internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsLongBufferB internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsIntBufferL internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsIntBufferB internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsShortBufferL internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsCharBufferL internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsCharBufferB internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)

class ByteBufferAsShortBufferB internal constructor(heapByteBuffer: HeapByteBuffer, i: Int, i1: Int, size: Int, size1: Int, off: Int)
