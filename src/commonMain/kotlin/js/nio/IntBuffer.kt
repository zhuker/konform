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

import jdk.Math
import js.lang.StringBuffer


/**
 * An int buffer.
 *
 *
 *  This class defines four categories of operations upon
 * int buffers:
 *
 *
 *
 *  *
 *
 * Absolute and relative [&lt;i&gt;get&lt;/i&gt;][.get] and
 * [&lt;i&gt;put&lt;/i&gt;][.put] methods that read and write
 * single ints;
 *
 *  *
 *
 * Relative [&lt;i&gt;bulk get&lt;/i&gt;][.get]
 * methods that transfer contiguous sequences of ints from this buffer
 * into an array; and
 *
 *  *
 *
 * Relative [&lt;i&gt;bulk put&lt;/i&gt;][.put]
 * methods that transfer contiguous sequences of ints from an
 * int array or some other int
 * buffer into this buffer;&#32;and
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
 *  *
 *
 * Methods for [compacting][.compact], [   ][.duplicate], and [slicing][.slice]
 * an int buffer.
 *
 *
 *
 *
 *  Int buffers can be created either by [ &lt;i&gt;allocation&lt;/i&gt;][.allocate], which allocates space for the buffer's
 *
 *
 *
 *
 *
 *
 *
 *
 * content, by [&lt;i&gt;wrapping&lt;/i&gt;][.wrap] an existing
 * int array  into a buffer, or by creating a
 * [*view*](ByteBuffer.html#views) of an existing byte buffer.
 *
 *
 *  Like a byte buffer, an int buffer is either [*direct* or *non-direct*](ByteBuffer.html#direct).  A
 * int buffer created via the <tt>wrap</tt> methods of this class will
 * be non-direct.  An int buffer created as a view of a byte buffer will
 * be direct if, and only if, the byte buffer itself is direct.  Whether or not
 * an int buffer is direct may be determined by invoking the [ ][.isDirect] method.
 *
 *
 *
 *  Methods in this class that do not otherwise have a value to return are
 * specified to return the buffer upon which they are invoked.  This allows
 * method invocations to be chained.
 *
 *
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 */

abstract class IntBuffer// Creates a new buffer with the given mark, position, limit, capacity,
// backing array, and array offset
//
//@JvmOverloads
internal constructor(mark: Int, pos: Int, lim: Int, cap: Int, // package-private
        // These fields are declared here rather than in Heap-X-Buffer in order to
        // reduce the number of virtual method invocations needed to access these
        // values, which is especially costly when coding small buffers.
        //
                                   internal open val hb: IntArray? = null                  // Non-null only for heap buffers
                                   , internal val offset: Int = 0) : Buffer(mark, pos, lim, cap), Comparable<IntBuffer> {
    override var isReadOnly: Boolean = false                 // Valid only for heap buffers


    /**
     * Creates a new int buffer whose content is a shared subsequence of
     * this buffer's content.
     *
     *
     *  The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     *
     *  The new buffer's position will be zero, its capacity and its limit
     * will be the number of ints remaining in this buffer, and its mark
     * will be undefined.  The new buffer will be direct if, and only if, this
     * buffer is direct, and it will be read-only if, and only if, this buffer
     * is read-only.
     *
     * @return  The new int buffer
     */
    abstract fun slice(): IntBuffer

    /**
     * Creates a new int buffer that shares this buffer's content.
     *
     *
     *  The content of the new buffer will be that of this buffer.  Changes
     * to this buffer's content will be visible in the new buffer, and vice
     * versa; the two buffers' position, limit, and mark values will be
     * independent.
     *
     *
     *  The new buffer's capacity, limit, position, and mark values will be
     * identical to those of this buffer.  The new buffer will be direct if,
     * and only if, this buffer is direct, and it will be read-only if, and
     * only if, this buffer is read-only.
     *
     * @return  The new int buffer
     */
    abstract fun duplicate(): IntBuffer

    /**
     * Creates a new, read-only int buffer that shares this buffer's
     * content.
     *
     *
     *  The content of the new buffer will be that of this buffer.  Changes
     * to this buffer's content will be visible in the new buffer; the new
     * buffer itself, however, will be read-only and will not allow the shared
     * content to be modified.  The two buffers' position, limit, and mark
     * values will be independent.
     *
     *
     *  The new buffer's capacity, limit, position, and mark values will be
     * identical to those of this buffer.
     *
     *
     *  If this buffer is itself read-only then this method behaves in
     * exactly the same way as the [duplicate][.duplicate] method.
     *
     * @return  The new, read-only int buffer
     */
    abstract fun asReadOnlyBuffer(): IntBuffer


    // -- Singleton get/put methods --

    /**
     * Relative *get* method.  Reads the int at this buffer's
     * current position, and then increments the position.
     *
     * @return  The int at the buffer's current position
     *
     * @throws  BufferUnderflowException
     * If the buffer's current position is not smaller than its limit
     */
    abstract fun get(): Int

    /**
     * Relative *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes the given int into this buffer at the current
     * position, and then increments the position.
     *
     * @param  i
     * The int to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If this buffer's current position is not smaller than its limit
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun put(i: Int): IntBuffer

    /**
     * Absolute *get* method.  Reads the int at the given
     * index.
     *
     * @param  index
     * The index from which the int will be read
     *
     * @return  The int at the given index
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit
     */
    abstract operator fun get(index: Int): Int


    /**
     * Absolute *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes the given int into this buffer at the given
     * index.
     *
     * @param  index
     * The index at which the int will be written
     *
     * @param  i
     * The int value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun put(index: Int, i: Int): IntBuffer


    // -- Bulk get operations --

    /**
     * Relative bulk *get* method.
     *
     *
     *  This method transfers ints from this buffer into the given
     * destination array.  If there are fewer ints remaining in the
     * buffer than are required to satisfy the request, that is, if
     * <tt>length</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>, then no
     * ints are transferred and a [BufferUnderflowException] is
     * thrown.
     *
     *
     *  Otherwise, this method copies <tt>length</tt> ints from this
     * buffer into the given array, starting at the current position of this
     * buffer and at the given offset in the array.  The position of this
     * buffer is then incremented by <tt>length</tt>.
     *
     *
     *  In other words, an invocation of this method of the form
     * <tt>src.get(dst,&nbsp;off,&nbsp;len)</tt> has exactly the same effect as
     * the loop
     *
     * <pre>`for (int i = off; i < off + len; i++)
     * dst[i] = src.get():
    `</pre> *
     *
     * except that it first checks that there are sufficient ints in
     * this buffer and it is potentially much more efficient.
     *
     * @param  dst
     * The array into which ints are to be written
     *
     * @param  offset
     * The offset within the array of the first int to be
     * written; must be non-negative and no larger than
     * <tt>dst.length</tt>
     *
     * @param  length
     * The maximum number of ints to be written to the given
     * array; must be non-negative and no larger than
     * <tt>dst.length - offset</tt>
     *
     * @return  This buffer
     *
     * @throws  BufferUnderflowException
     * If there are fewer than <tt>length</tt> ints
     * remaining in this buffer
     *
     * @throws  IndexOutOfBoundsException
     * If the preconditions on the <tt>offset</tt> and <tt>length</tt>
     * parameters do not hold
     */
    open operator fun get(dst: IntArray, offset: Int, length: Int): IntBuffer {
        Buffer.checkBounds(offset, length, dst.size)
        if (length > remaining())
            throw BufferUnderflowException()
        val end = offset + length
        for (i in offset until end)
            dst[i] = get()
        return this
    }

    /**
     * Relative bulk *get* method.
     *
     *
     *  This method transfers ints from this buffer into the given
     * destination array.  An invocation of this method of the form
     * <tt>src.get(a)</tt> behaves in exactly the same way as the invocation
     *
     * <pre>
     * src.get(a, 0, a.length) </pre>
     *
     * @param   dst
     * The destination array
     *
     * @return  This buffer
     *
     * @throws  BufferUnderflowException
     * If there are fewer than <tt>length</tt> ints
     * remaining in this buffer
     */
    operator fun get(dst: IntArray): IntBuffer {
        return get(dst, 0, dst.size)
    }


    // -- Bulk put operations --

    /**
     * Relative bulk *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  This method transfers the ints remaining in the given source
     * buffer into this buffer.  If there are more ints remaining in the
     * source buffer than in this buffer, that is, if
     * <tt>src.remaining()</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>,
     * then no ints are transferred and a [ ] is thrown.
     *
     *
     *  Otherwise, this method copies
     * *n*&nbsp;=&nbsp;<tt>src.remaining()</tt> ints from the given
     * buffer into this buffer, starting at each buffer's current position.
     * The positions of both buffers are then incremented by *n*.
     *
     *
     *  In other words, an invocation of this method of the form
     * <tt>dst.put(src)</tt> has exactly the same effect as the loop
     *
     * <pre>
     * while (src.hasRemaining())
     * dst.put(src.get()); </pre>
     *
     * except that it first checks that there is sufficient space in this
     * buffer and it is potentially much more efficient.
     *
     * @param  src
     * The source buffer from which ints are to be read;
     * must not be this buffer
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     * for the remaining ints in the source buffer
     *
     * @throws  IllegalArgumentException
     * If the source buffer is this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    open fun put(src: IntBuffer): IntBuffer {
        if (src === this)
            throw IllegalArgumentException()
        if (isReadOnly)
            throw ReadOnlyBufferException()
        val n = src.remaining()
        if (n > remaining())
            throw BufferOverflowException()
        for (i in 0 until n)
            put(src.get())
        return this
    }

    /**
     * Relative bulk *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  This method transfers ints into this buffer from the given
     * source array.  If there are more ints to be copied from the array
     * than remain in this buffer, that is, if
     * <tt>length</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>, then no
     * ints are transferred and a [BufferOverflowException] is
     * thrown.
     *
     *
     *  Otherwise, this method copies <tt>length</tt> ints from the
     * given array into this buffer, starting at the given offset in the array
     * and at the current position of this buffer.  The position of this buffer
     * is then incremented by <tt>length</tt>.
     *
     *
     *  In other words, an invocation of this method of the form
     * <tt>dst.put(src,&nbsp;off,&nbsp;len)</tt> has exactly the same effect as
     * the loop
     *
     * <pre>`for (int i = off; i < off + len; i++)
     * dst.put(a[i]);
    `</pre> *
     *
     * except that it first checks that there is sufficient space in this
     * buffer and it is potentially much more efficient.
     *
     * @param  src
     * The array from which ints are to be read
     *
     * @param  offset
     * The offset within the array of the first int to be read;
     * must be non-negative and no larger than <tt>array.length</tt>
     *
     * @param  length
     * The number of ints to be read from the given array;
     * must be non-negative and no larger than
     * <tt>array.length - offset</tt>
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     *
     * @throws  IndexOutOfBoundsException
     * If the preconditions on the <tt>offset</tt> and <tt>length</tt>
     * parameters do not hold
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    open fun put(src: IntArray, offset: Int, length: Int): IntBuffer {
        Buffer.checkBounds(offset, length, src.size)
        if (length > remaining())
            throw BufferOverflowException()
        val end = offset + length
        for (i in offset until end)
            this.put(src[i])
        return this
    }

    /**
     * Relative bulk *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  This method transfers the entire content of the given source
     * int array into this buffer.  An invocation of this method of the
     * form <tt>dst.put(a)</tt> behaves in exactly the same way as the
     * invocation
     *
     * <pre>
     * dst.put(a, 0, a.length) </pre>
     *
     * @param   src
     * The source array
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    fun put(src: IntArray): IntBuffer {
        return put(src, 0, src.size)
    }


    // -- Other stuff --

    /**
     * Tells whether or not this buffer is backed by an accessible int
     * array.
     *
     *
     *  If this method returns <tt>true</tt> then the [array][.array]
     * and [arrayOffset][.arrayOffset] methods may safely be invoked.
     *
     *
     * @return  <tt>true</tt> if, and only if, this buffer
     * is backed by an array and is not read-only
     */
    override fun hasArray(): Boolean {
        return hb != null && !isReadOnly
    }

    /**
     * Returns the int array that backs this
     * buffer&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Modifications to this buffer's content will cause the returned
     * array's content to be modified, and vice versa.
     *
     *
     *  Invoke the [hasArray][.hasArray] method before invoking this
     * method in order to ensure that this buffer has an accessible backing
     * array.
     *
     * @return  The array that backs this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is backed by an array but is read-only
     *
     * @throws  UnsupportedOperationException
     * If this buffer is not backed by an accessible array
     */
    override fun array(): IntArray {
        if (hb == null)
            throw UnsupportedOperationException()
        if (isReadOnly)
            throw ReadOnlyBufferException()
        return hb!!
    }

    /**
     * Returns the offset within this buffer's backing array of the first
     * element of the buffer&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  If this buffer is backed by an array then buffer position *p*
     * corresponds to array index *p*&nbsp;+&nbsp;<tt>arrayOffset()</tt>.
     *
     *
     *  Invoke the [hasArray][.hasArray] method before invoking this
     * method in order to ensure that this buffer has an accessible backing
     * array.
     *
     * @return  The offset within this buffer's array
     * of the first element of the buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is backed by an array but is read-only
     *
     * @throws  UnsupportedOperationException
     * If this buffer is not backed by an accessible array
     */
    override fun arrayOffset(): Int {
        if (hb == null)
            throw UnsupportedOperationException()
        if (isReadOnly)
            throw ReadOnlyBufferException()
        return offset
    }

    /**
     * Compacts this buffer&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  The ints between the buffer's current position and its limit,
     * if any, are copied to the beginning of the buffer.  That is, the
     * int at index *p*&nbsp;=&nbsp;<tt>position()</tt> is copied
     * to index zero, the int at index *p*&nbsp;+&nbsp;1 is copied
     * to index one, and so forth until the int at index
     * <tt>limit()</tt>&nbsp;-&nbsp;1 is copied to index
     * *n*&nbsp;=&nbsp;<tt>limit()</tt>&nbsp;-&nbsp;<tt>1</tt>&nbsp;-&nbsp;*p*.
     * The buffer's position is then set to *n+1* and its limit is set to
     * its capacity.  The mark, if defined, is discarded.
     *
     *
     *  The buffer's position is set to the number of ints copied,
     * rather than to zero, so that an invocation of this method can be
     * followed immediately by an invocation of another relative *put*
     * method.
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
     * @return  This buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun compact(): IntBuffer

    /**
     * Tells whether or not this int buffer is direct.
     *
     * @return  <tt>true</tt> if, and only if, this buffer is direct
     */
    abstract override fun isDirect(): Boolean


    /**
     * Returns a string summarizing the state of this buffer.
     *
     * @return  A summary string
     */
    override fun toString(): String {
        val sb = StringBuffer()
        sb.append(this::class.simpleName)
        sb.append("[pos=")
        sb.append(position())
        sb.append(" lim=")
        sb.append(limit())
        sb.append(" cap=")
        sb.append(capacity())
        sb.append("]")
        return sb.toString()
    }


    /**
     * Returns the current hash code of this buffer.
     *
     *
     *  The hash code of a int buffer depends only upon its remaining
     * elements; that is, upon the elements from <tt>position()</tt> up to, and
     * including, the element at <tt>limit()</tt>&nbsp;-&nbsp;<tt>1</tt>.
     *
     *
     *  Because buffer hash codes are content-dependent, it is inadvisable
     * to use buffers as keys in hash maps or similar data structures unless it
     * is known that their contents will not change.
     *
     * @return  The current hash code of this buffer
     */
    override fun hashCode(): Int {
        var h = 1
        val p = position()
        for (i in limit() - 1 downTo p)

            h = 31 * h + get(i)



        return h
    }

    /**
     * Tells whether or not this buffer is equal to another object.
     *
     *
     *  Two int buffers are equal if, and only if,
     *
     *
     *
     *  1.
     *
     * They have the same element type,
     *
     *  *
     *
     * They have the same number of remaining elements, and
     *
     *
     *  *
     *
     * The two sequences of remaining elements, considered
     * independently of their starting positions, are pointwise equal.
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
     *  A int buffer is not equal to any other type of object.
     *
     * @param  ob  The object to which this buffer is to be compared
     *
     * @return  <tt>true</tt> if, and only if, this buffer is equal to the
     * given object
     */
    override fun equals(ob: Any?): Boolean {
        if (this === ob)
            return true
        if (ob !is IntBuffer)
            return false
        val that = ob as IntBuffer?
        if (this.remaining() != that!!.remaining())
            return false
        val p = this.position()
        var i = this.limit() - 1
        var j = that.limit() - 1
        while (i >= p) {
            if (!equals(this.get(i), that.get(j)))
                return false
            i--
            j--
        }
        return true
    }

    /**
     * Compares this buffer to another.
     *
     *
     *  Two int buffers are compared by comparing their sequences of
     * remaining elements lexicographically, without regard to the starting
     * position of each sequence within its corresponding buffer.
     *
     *
     *
     *
     *
     *
     *
     *
     * Pairs of `int` elements are compared as if by invoking
     * [Integer.compare].
     *
     *
     *
     *  A int buffer is not comparable to any other type of object.
     *
     * @return  A negative integer, zero, or a positive integer as this buffer
     * is less than, equal to, or greater than the given buffer
     */
    override fun compareTo(that: IntBuffer): Int {
        val n = this.position() + Math.min(this.remaining(), that.remaining())
        var i = this.position()
        var j = that.position()
        while (i < n) {
            val cmp = compare(this.get(i), that.get(j))
            if (cmp != 0)
                return cmp
            i++
            j++
        }
        return this.remaining() - that.remaining()
    }

    // -- Other char stuff --


    // -- Other byte stuff: Access to binary data --


    /**
     * Retrieves this buffer's byte order.
     *
     *
     *  The byte order of an int buffer created by allocation or by
     * wrapping an existing <tt>int</tt> array is the [ ][ByteOrder.nativeOrder] of the underlying
     * hardware.  The byte order of an int buffer created as a [view](ByteBuffer.html#views) of a byte buffer is that of the
     * byte buffer at the moment that the view is created.
     *
     * @return  This buffer's byte order
     */
    abstract fun order(): ByteOrder

    companion object {


        /**
         * Allocates a new int buffer.
         *
         *
         *  The new buffer's position will be zero, its limit will be its
         * capacity, its mark will be undefined, and each of its elements will be
         * initialized to zero.  It will have a [backing array][.array],
         * and its [array offset][.arrayOffset] will be zero.
         *
         * @param  capacity
         * The new buffer's capacity, in ints
         *
         * @return  The new int buffer
         *
         * @throws  IllegalArgumentException
         * If the <tt>capacity</tt> is a negative integer
         */
        fun allocate(capacity: Int): IntBuffer {
            if (capacity < 0)
                throw IllegalArgumentException()
            return HeapIntBuffer(capacity, capacity)
        }

        /**
         * Wraps an int array into a buffer.
         *
         *
         *  The new buffer will be backed by the given int array;
         * that is, modifications to the buffer will cause the array to be modified
         * and vice versa.  The new buffer's capacity will be
         * <tt>array.length</tt>, its position will be <tt>offset</tt>, its limit
         * will be <tt>offset + length</tt>, and its mark will be undefined.  Its
         * [backing array][.array] will be the given array, and
         * its [array offset][.arrayOffset] will be zero.
         *
         * @param  array
         * The array that will back the new buffer
         *
         * @param  offset
         * The offset of the subarray to be used; must be non-negative and
         * no larger than <tt>array.length</tt>.  The new buffer's position
         * will be set to this value.
         *
         * @param  length
         * The length of the subarray to be used;
         * must be non-negative and no larger than
         * <tt>array.length - offset</tt>.
         * The new buffer's limit will be set to <tt>offset + length</tt>.
         *
         * @return  The new int buffer
         *
         * @throws  IndexOutOfBoundsException
         * If the preconditions on the <tt>offset</tt> and <tt>length</tt>
         * parameters do not hold
         */
//        @JvmOverloads
        fun wrap(array: IntArray,
                 offset: Int = 0, length: Int = array.size): IntBuffer {
            try {
                return HeapIntBuffer(array, offset, length)
            } catch (x: IllegalArgumentException) {
                throw IndexOutOfBoundsException()
            }

        }

        private fun equals(x: Int, y: Int): Boolean {


            return x == y

        }

        private fun compare(x: Int, y: Int): Int {


            return x.compareTo(y)

        }
    }


}// Creates a new buffer with the given mark, position, limit, and capacity
//
// package-private
/**
 * Wraps an int array into a buffer.
 *
 *
 *  The new buffer will be backed by the given int array;
 * that is, modifications to the buffer will cause the array to be modified
 * and vice versa.  The new buffer's capacity and limit will be
 * <tt>array.length</tt>, its position will be zero, and its mark will be
 * undefined.  Its [backing array][.array] will be the
 * given array, and its [array offset&gt;][.arrayOffset] will
 * be zero.
 *
 * @param  array
 * The array that will back this buffer
 *
 * @return  The new int buffer
 */
