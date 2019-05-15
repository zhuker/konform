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
 * A read/write HeapIntBuffer.
 *
 *
 *
 *
 *
 *
 */

internal open class HeapIntBuffer
(override val hb: IntArray,
 mrk: Int = -1,
 pos: Int = 0,
 lim: Int,
 cap: Int,
 off: Int
) : IntBuffer(mrk, pos, lim, cap, hb, off) {

    override fun isDirect(): Boolean = false


    override var isReadOnly: Boolean = false

    // For speed these fields are actually declared in X-Buffer;
    // these declarations are here as documentation
    /*

    protected final int[] hb;
    protected final int offset;

    */
    constructor(cap: Int, lim: Int) : this(IntArray(cap), -1, 0, lim, cap, 0)
    constructor(buf: IntArray, off: Int, len: Int) : this(buf, -1, 0, off + len, buf.size, off)

//    constructor(cap: Int, lim: Int) : super(-1, 0, lim, cap, IntArray(cap), 0) {            // package-private
    /*
    hb = new int[cap];
    offset = 0;
    */


//    }

//    constructor(buf: IntArray, off: Int, len: Int) : super(-1, off, off + len, buf.size, buf, 0) { // package-private
    /*
    hb = buf;
    offset = 0;
    */


//    }

//    protected constructor(buf: IntArray,
//                          mark: Int, pos: Int, lim: Int, cap: Int,
//                          off: Int) : super(mark, pos, lim, cap, buf, off) {
    /*
    hb = buf;
    offset = off;
    */


//    }

    override fun slice(): IntBuffer {
        return HeapIntBuffer(hb,
                -1,
                0,
                this.remaining(),
                this.remaining(),
                this.position() + offset)
    }

    override fun duplicate(): IntBuffer {
        return HeapIntBuffer(hb,
                this.markValue(),
                this.position(),
                this.limit(),
                this.capacity(),
                offset)
    }

    override fun asReadOnlyBuffer(): IntBuffer {

        return HeapIntBufferR(hb,
                this.markValue(),
                this.position(),
                this.limit(),
                this.capacity(),
                offset)


    }


    protected fun ix(i: Int): Int {
        return i + offset
    }

    override fun get(): Int {
        return hb[ix(nextGetIndex())]
    }

    override fun get(i: Int): Int {
        return hb[ix(checkIndex(i))]
    }


    override fun get(dst: IntArray, offset: Int, length: Int): IntBuffer {
        checkBounds(offset, length, dst.size)
        if (length > remaining())
            throw BufferUnderflowException()
        System.arraycopy(hb, ix(position()), dst, offset, length)
        position(position() + length)
        return this
    }

    override fun put(x: Int): IntBuffer {

        hb[ix(nextPutIndex())] = x
        return this


    }

    override fun put(i: Int, x: Int): IntBuffer {

        hb[ix(checkIndex(i))] = x
        return this


    }

    override fun put(src: IntArray, offset: Int, length: Int): IntBuffer {

        checkBounds(offset, length, src.size)
        if (length > remaining())
            throw BufferOverflowException()
        System.arraycopy(src, offset, hb, ix(position()), length)
        position(position() + length)
        return this


    }

    override fun put(src: IntBuffer): IntBuffer {

        if (src is HeapIntBuffer) {
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

    override fun compact(): IntBuffer {

        System.arraycopy(hb, ix(position()), hb, ix(0), remaining())
        position(remaining())
        limit(capacity())
        discardMark()
        return this


    }


    override fun order(): ByteOrder {
        return ByteOrder.nativeOrder()
    }


}

internal class HeapIntBufferR(hb: IntArray, markValue: Int, position: Int, limit: Int, capacity: Int, offset: Int) : HeapIntBuffer(hb, markValue, position, limit, capacity, offset)
