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

import js.lang.charAt


//import java.io.IOException
//
//
//import java.util.Spliterator
//import java.util.stream.StreamSupport
//import java.util.stream.IntStream


/**
 * A char buffer.
 *
 *
 *  This class defines four categories of operations upon
 * char buffers:
 *
 *
 *
 *  *
 *
 * Absolute and relative [&lt;i&gt;get&lt;/i&gt;][.get] and
 * [&lt;i&gt;put&lt;/i&gt;][.put] methods that read and write
 * single chars;
 *
 *  *
 *
 * Relative [&lt;i&gt;bulk get&lt;/i&gt;][.get]
 * methods that transfer contiguous sequences of chars from this buffer
 * into an array; and
 *
 *  *
 *
 * Relative [&lt;i&gt;bulk put&lt;/i&gt;][.put]
 * methods that transfer contiguous sequences of chars from a
 * char array,&#32;a&#32;string, or some other char
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
 * a char buffer.
 *
 *
 *
 *
 *  Char buffers can be created either by [ &lt;i&gt;allocation&lt;/i&gt;][.allocate], which allocates space for the buffer's
 *
 *
 *
 *
 *
 *
 *
 *
 * content, by [&lt;i&gt;wrapping&lt;/i&gt;][.wrap] an existing
 * char array or&#32;string into a buffer, or by creating a
 * [*view*](ByteBuffer.html#views) of an existing byte buffer.
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
 *
 *
 *
 *
 *
 *
 *  Like a byte buffer, a char buffer is either [*direct* or *non-direct*](ByteBuffer.html#direct).  A
 * char buffer created via the <tt>wrap</tt> methods of this class will
 * be non-direct.  A char buffer created as a view of a byte buffer will
 * be direct if, and only if, the byte buffer itself is direct.  Whether or not
 * a char buffer is direct may be determined by invoking the [ ][.isDirect] method.
 *
 *
 *
 *
 *
 *
 *  This class implements the [CharSequence] interface so that
 * character buffers may be used wherever character sequences are accepted, for
 * example in the regular-expression package <tt>[java.util.regex]</tt>.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *  Methods in this class that do not otherwise have a value to return are
 * specified to return the buffer upon which they are invoked.  This allows
 * method invocations to be chained.
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
 * The sequence of statements
 *
 * <blockquote><pre>
 * cb.put("text/");
 * cb.put(subtype);
 * cb.put("; charset=");
 * cb.put(enc);</pre></blockquote>
 *
 * can, for example, be replaced by the single statement
 *
 * <blockquote><pre>
 * cb.put("text/").put(subtype).put("; charset=").put(enc);</pre></blockquote>
 *
 *
 *
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 */
// Creates a new buffer with the given mark, position, limit, capacity,
// backing array, and array offset
//
abstract class CharBuffer
internal constructor(mark: Int, pos: Int, lim: Int, cap: Int, // package-private
        // These fields are declared here rather than in Heap-X-Buffer in order to
        // reduce the number of virtual method invocations needed to access these
        // values, which is especially costly when coding small buffers.
        //
                                   internal val hb: CharArray? = null                  // Non-null only for heap buffers
                                   , internal val offset: Int = 0) : Buffer(mark, pos, lim, cap), Comparable<CharBuffer>, Appendable, CharSequence
//        , Readable
{
    override var isReadOnly: Boolean = false                 // Valid only for heap buffers

    /**
     * Tells whether or not this char buffer is direct.
     *
     * @return  <tt>true</tt> if, and only if, this buffer is direct
     */
    abstract override fun isDirect(): Boolean


    /**
     * Attempts to read characters into the specified character buffer.
     * The buffer is used as a repository of characters as-is: the only
     * changes made are the results of a put operation. No flipping or
     * rewinding of the buffer is performed.
     *
     * @param target the buffer to read characters into
     * @return The number of characters added to the buffer, or
     * -1 if this source of characters is at its end
     * @throws IOException if an I/O error occurs
     * @throws NullPointerException if target is null
     * @throws ReadOnlyBufferException if target is a read only buffer
     * @since 1.5
     */
//    @Throws(IOException::class)
    fun read(target: CharBuffer): Int {
        // Determine the number of bytes n that can be transferred
        val targetRemaining = target.remaining()
        val remaining = remaining()
        if (remaining == 0)
            return -1
        val n = kotlin.math.min(remaining, targetRemaining)
        val limit = limit()
        // Set source limit to prevent target overflow
        if (targetRemaining < remaining)
            limit(position() + n)
        try {
            if (n > 0)
                target.put(this)
        } finally {
            limit(limit) // restore real limit
        }
        return n
    }


    /**
     * Creates a new char buffer whose content is a shared subsequence of
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
     * will be the number of chars remaining in this buffer, and its mark
     * will be undefined.  The new buffer will be direct if, and only if, this
     * buffer is direct, and it will be read-only if, and only if, this buffer
     * is read-only.
     *
     * @return  The new char buffer
     */
    abstract fun slice(): CharBuffer

    /**
     * Creates a new char buffer that shares this buffer's content.
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
     * @return  The new char buffer
     */
    abstract fun duplicate(): CharBuffer

    /**
     * Creates a new, read-only char buffer that shares this buffer's
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
     * @return  The new, read-only char buffer
     */
    abstract fun asReadOnlyBuffer(): CharBuffer


    // -- Singleton get/put methods --

    /**
     * Relative *get* method.  Reads the char at this buffer's
     * current position, and then increments the position.
     *
     * @return  The char at the buffer's current position
     *
     * @throws  BufferUnderflowException
     * If the buffer's current position is not smaller than its limit
     */
    abstract fun get(): Char

    /**
     * Relative *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes the given char into this buffer at the current
     * position, and then increments the position.
     *
     * @param  c
     * The char to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If this buffer's current position is not smaller than its limit
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun put(c: Char): CharBuffer

    /**
     * Absolute *get* method.  Reads the char at the given
     * index.
     *
     * @param  index
     * The index from which the char will be read
     *
     * @return  The char at the given index
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit
     */
    abstract override fun get(index: Int): Char


    /**
     * Absolute *get* method.  Reads the char at the given
     * index without any validation of the index.
     *
     * @param  index
     * The index from which the char will be read
     *
     * @return  The char at the given index
     */
    internal abstract fun getUnchecked(index: Int): Char    // package-private


    /**
     * Absolute *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes the given char into this buffer at the given
     * index.
     *
     * @param  index
     * The index at which the char will be written
     *
     * @param  c
     * The char value to be written
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
    abstract fun put(index: Int, c: Char): CharBuffer


    // -- Bulk get operations --

    /**
     * Relative bulk *get* method.
     *
     *
     *  This method transfers chars from this buffer into the given
     * destination array.  If there are fewer chars remaining in the
     * buffer than are required to satisfy the request, that is, if
     * <tt>length</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>, then no
     * chars are transferred and a [BufferUnderflowException] is
     * thrown.
     *
     *
     *  Otherwise, this method copies <tt>length</tt> chars from this
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
     * except that it first checks that there are sufficient chars in
     * this buffer and it is potentially much more efficient.
     *
     * @param  dst
     * The array into which chars are to be written
     *
     * @param  offset
     * The offset within the array of the first char to be
     * written; must be non-negative and no larger than
     * <tt>dst.length</tt>
     *
     * @param  length
     * The maximum number of chars to be written to the given
     * array; must be non-negative and no larger than
     * <tt>dst.length - offset</tt>
     *
     * @return  This buffer
     *
     * @throws  BufferUnderflowException
     * If there are fewer than <tt>length</tt> chars
     * remaining in this buffer
     *
     * @throws  IndexOutOfBoundsException
     * If the preconditions on the <tt>offset</tt> and <tt>length</tt>
     * parameters do not hold
     */
//    @JvmOverloads
    operator fun get(dst: CharArray, offset: Int = 0, length: Int = dst.size): CharBuffer {
        checkBounds(offset, length, dst.size)
        if (length > remaining())
            throw BufferUnderflowException()
        val end = offset + length
        for (i in offset until end)
            dst[i] = get()
        return this
    }


    // -- Bulk put operations --

    /**
     * Relative bulk *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  This method transfers the chars remaining in the given source
     * buffer into this buffer.  If there are more chars remaining in the
     * source buffer than in this buffer, that is, if
     * <tt>src.remaining()</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>,
     * then no chars are transferred and a [ ] is thrown.
     *
     *
     *  Otherwise, this method copies
     * *n*&nbsp;=&nbsp;<tt>src.remaining()</tt> chars from the given
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
     * The source buffer from which chars are to be read;
     * must not be this buffer
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     * for the remaining chars in the source buffer
     *
     * @throws  IllegalArgumentException
     * If the source buffer is this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    fun put(src: CharBuffer): CharBuffer {
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
     *  This method transfers chars into this buffer from the given
     * source array.  If there are more chars to be copied from the array
     * than remain in this buffer, that is, if
     * <tt>length</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>, then no
     * chars are transferred and a [BufferOverflowException] is
     * thrown.
     *
     *
     *  Otherwise, this method copies <tt>length</tt> chars from the
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
     * The array from which chars are to be read
     *
     * @param  offset
     * The offset within the array of the first char to be read;
     * must be non-negative and no larger than <tt>array.length</tt>
     *
     * @param  length
     * The number of chars to be read from the given array;
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
//    @JvmOverloads
    fun put(src: CharArray, offset: Int = 0, length: Int = src.size): CharBuffer {
        checkBounds(offset, length, src.size)
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
     *  This method transfers chars from the given string into this
     * buffer.  If there are more chars to be copied from the string than
     * remain in this buffer, that is, if
     * <tt>end&nbsp;-&nbsp;start</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>,
     * then no chars are transferred and a [ ] is thrown.
     *
     *
     *  Otherwise, this method copies
     * *n*&nbsp;=&nbsp;<tt>end</tt>&nbsp;-&nbsp;<tt>start</tt> chars
     * from the given string into this buffer, starting at the given
     * <tt>start</tt> index and at the current position of this buffer.  The
     * position of this buffer is then incremented by *n*.
     *
     *
     *  In other words, an invocation of this method of the form
     * <tt>dst.put(src,&nbsp;start,&nbsp;end)</tt> has exactly the same effect
     * as the loop
     *
     * <pre>`for (int i = start; i < end; i++)
     * dst.put(src.charAt(i));
    `</pre> *
     *
     * except that it first checks that there is sufficient space in this
     * buffer and it is potentially much more efficient.
     *
     * @param  src
     * The string from which chars are to be read
     *
     * @param  start
     * The offset within the string of the first char to be read;
     * must be non-negative and no larger than
     * <tt>string.length()</tt>
     *
     * @param  end
     * The offset within the string of the last char to be read,
     * plus one; must be non-negative and no larger than
     * <tt>string.length()</tt>
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     *
     * @throws  IndexOutOfBoundsException
     * If the preconditions on the <tt>start</tt> and <tt>end</tt>
     * parameters do not hold
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    fun put(src: String, start: Int, end: Int): CharBuffer {
        checkBounds(start, end - start, src.length)
        if (isReadOnly)
            throw ReadOnlyBufferException()
        if (end - start > remaining())
            throw BufferOverflowException()
        for (i in start until end)
            this.put(src.charAt(i))
        return this
    }

    /**
     * Relative bulk *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  This method transfers the entire content of the given source string
     * into this buffer.  An invocation of this method of the form
     * <tt>dst.put(s)</tt> behaves in exactly the same way as the invocation
     *
     * <pre>
     * dst.put(s, 0, s.length()) </pre>
     *
     * @param   src
     * The source string
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    fun put(src: String): CharBuffer {
        return put(src, 0, src.length)
    }


    // -- Other stuff --

    /**
     * Tells whether or not this buffer is backed by an accessible char
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
     * Returns the char array that backs this
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
    override fun array(): CharArray {
        if (hb == null)
            throw UnsupportedOperationException()
        if (isReadOnly)
            throw ReadOnlyBufferException()
        return hb
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
     *  The chars between the buffer's current position and its limit,
     * if any, are copied to the beginning of the buffer.  That is, the
     * char at index *p*&nbsp;=&nbsp;<tt>position()</tt> is copied
     * to index zero, the char at index *p*&nbsp;+&nbsp;1 is copied
     * to index one, and so forth until the char at index
     * <tt>limit()</tt>&nbsp;-&nbsp;1 is copied to index
     * *n*&nbsp;=&nbsp;<tt>limit()</tt>&nbsp;-&nbsp;<tt>1</tt>&nbsp;-&nbsp;*p*.
     * The buffer's position is then set to *n+1* and its limit is set to
     * its capacity.  The mark, if defined, is discarded.
     *
     *
     *  The buffer's position is set to the number of chars copied,
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
    abstract fun compact(): CharBuffer


    /**
     * Returns the current hash code of this buffer.
     *
     *
     *  The hash code of a char buffer depends only upon its remaining
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


            h = 31 * h + get(i).toInt()

        return h
    }

    /**
     * Tells whether or not this buffer is equal to another object.
     *
     *
     *  Two char buffers are equal if, and only if,
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
     *  A char buffer is not equal to any other type of object.
     *
     * @param  ob  The object to which this buffer is to be compared
     *
     * @return  <tt>true</tt> if, and only if, this buffer is equal to the
     * given object
     */
    override fun equals(ob: Any?): Boolean {
        if (this === ob)
            return true
        if (ob !is CharBuffer)
            return false
        val that = ob as CharBuffer
        if (this.remaining() != that.remaining())
            return false
        val p = this.position()
        var i = this.limit() - 1
        var j = that.limit() - 1
        while (i >= p) {
            if (!equals(this[i], that[j]))
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
     *  Two char buffers are compared by comparing their sequences of
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
     * Pairs of `char` elements are compared as if by invoking
     * [Character.compare].
     *
     *
     *
     *  A char buffer is not comparable to any other type of object.
     *
     * @return  A negative integer, zero, or a positive integer as this buffer
     * is less than, equal to, or greater than the given buffer
     */
    override fun compareTo(that: CharBuffer): Int {
        val n = this.position() + kotlin.math.min(this.remaining(), that.remaining())
        var i = this.position()
        var j = that.position()
        while (i < n) {
            val cmp = compare(this[i], that[j])
            if (cmp != 0)
                return cmp
            i++
            j++
        }
        return this.remaining() - that.remaining()
    }

    // -- Other char stuff --


    /**
     * Returns a string containing the characters in this buffer.
     *
     *
     *  The first character of the resulting string will be the character at
     * this buffer's position, while the last character will be the character
     * at index <tt>limit()</tt>&nbsp;-&nbsp;1.  Invoking this method does not
     * change the buffer's position.
     *
     * @return  The specified string
     */
    override fun toString(): String {
        return toString(position(), limit())
    }

    internal abstract fun toString(start: Int, end: Int): String        // package-private


    // --- Methods to support CharSequence ---

    /**
     * Returns the length of this character buffer.
     *
     *
     *  When viewed as a character sequence, the length of a character
     * buffer is simply the number of characters between the position
     * (inclusive) and the limit (exclusive); that is, it is equivalent to
     * <tt>remaining()</tt>.
     *
     * @return  The length of this character buffer
     */
    override val length: Int
        get() {
            return remaining()
        }

    /**
     * Reads the character at the given index relative to the current
     * position.
     *
     * @param  index
     * The index of the character to be read, relative to the position;
     * must be non-negative and smaller than <tt>remaining()</tt>
     *
     * @return  The character at index
     * <tt>position()&nbsp;+&nbsp;index</tt>
     *
     * @throws  IndexOutOfBoundsException
     * If the preconditions on <tt>index</tt> do not hold
     */
//    fun charAt(index: Int): Char {
//        return get(position() + checkIndex(index, 1))
//    }

    /**
     * Creates a new character buffer that represents the specified subsequence
     * of this buffer, relative to the current position.
     *
     *
     *  The new buffer will share this buffer's content; that is, if the
     * content of this buffer is mutable then modifications to one buffer will
     * cause the other to be modified.  The new buffer's capacity will be that
     * of this buffer, its position will be
     * <tt>position()</tt>&nbsp;+&nbsp;<tt>start</tt>, and its limit will be
     * <tt>position()</tt>&nbsp;+&nbsp;<tt>end</tt>.  The new buffer will be
     * direct if, and only if, this buffer is direct, and it will be read-only
     * if, and only if, this buffer is read-only.
     *
     * @param  start
     * The index, relative to the current position, of the first
     * character in the subsequence; must be non-negative and no larger
     * than <tt>remaining()</tt>
     *
     * @param  end
     * The index, relative to the current position, of the character
     * following the last character in the subsequence; must be no
     * smaller than <tt>start</tt> and no larger than
     * <tt>remaining()</tt>
     *
     * @return  The new character buffer
     *
     * @throws  IndexOutOfBoundsException
     * If the preconditions on <tt>start</tt> and <tt>end</tt>
     * do not hold
     */
    abstract override fun subSequence(start: Int, end: Int): CharBuffer


    // --- Methods to support Appendable ---

    /**
     * Appends the specified character sequence  to this
     * buffer&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  An invocation of this method of the form <tt>dst.append(csq)</tt>
     * behaves in exactly the same way as the invocation
     *
     * <pre>
     * dst.put(csq.toString()) </pre>
     *
     *
     *  Depending on the specification of <tt>toString</tt> for the
     * character sequence <tt>csq</tt>, the entire sequence may not be
     * appended.  For instance, invoking the [ toString][CharBuffer.toString] method of a character buffer will return a subsequence whose
     * content depends upon the buffer's position and limit.
     *
     * @param  csq
     * The character sequence to append.  If <tt>csq</tt> is
     * <tt>null</tt>, then the four characters <tt>"null"</tt> are
     * appended to this character buffer.
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     *
     * @since  1.5
     */
    override fun append(csq: CharSequence?): CharBuffer {
        return if (csq == null)
            put("null")
        else
            put(csq.toString())
    }

    /**
     * Appends a subsequence of the  specified character sequence  to this
     * buffer&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  An invocation of this method of the form <tt>dst.append(csq, start,
     * end)</tt> when <tt>csq</tt> is not <tt>null</tt>, behaves in exactly the
     * same way as the invocation
     *
     * <pre>
     * dst.put(csq.subSequence(start, end).toString()) </pre>
     *
     * @param  csq
     * The character sequence from which a subsequence will be
     * appended.  If <tt>csq</tt> is <tt>null</tt>, then characters
     * will be appended as if <tt>csq</tt> contained the four
     * characters <tt>"null"</tt>.
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>start</tt> or <tt>end</tt> are negative, <tt>start</tt>
     * is greater than <tt>end</tt>, or <tt>end</tt> is greater than
     * <tt>csq.length()</tt>
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     *
     * @since  1.5
     */
    override fun append(csq: CharSequence?, start: Int, end: Int): CharBuffer {
        val cs = csq ?: "null"
        return put(cs.subSequence(start, end).toString())
    }

    /**
     * Appends the specified char  to this
     * buffer&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  An invocation of this method of the form <tt>dst.append(c)</tt>
     * behaves in exactly the same way as the invocation
     *
     * <pre>
     * dst.put(c) </pre>
     *
     * @param  c
     * The 16-bit char to append
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     *
     * @since  1.5
     */
    override fun append(c: Char): CharBuffer {
        return put(c)
    }


    // -- Other byte stuff: Access to binary data --


    /**
     * Retrieves this buffer's byte order.
     *
     *
     *  The byte order of a char buffer created by allocation or by
     * wrapping an existing <tt>char</tt> array is the [ ][ByteOrder.nativeOrder] of the underlying
     * hardware.  The byte order of a char buffer created as a [view](ByteBuffer.html#views) of a byte buffer is that of the
     * byte buffer at the moment that the view is created.
     *
     * @return  This buffer's byte order
     */
    abstract fun order(): ByteOrder


//    @Override
//    fun chars(): IntStream {
//        return StreamSupport.intStream({ CharBufferSpliterator(this) },
//                Buffer.SPLITERATOR_CHARACTERISTICS, false)
//    }

    companion object {


        /**
         * Allocates a new char buffer.
         *
         *
         *  The new buffer's position will be zero, its limit will be its
         * capacity, its mark will be undefined, and each of its elements will be
         * initialized to zero.  It will have a [backing array][.array],
         * and its [array offset][.arrayOffset] will be zero.
         *
         * @param  capacity
         * The new buffer's capacity, in chars
         *
         * @return  The new char buffer
         *
         * @throws  IllegalArgumentException
         * If the <tt>capacity</tt> is a negative integer
         */
        fun allocate(capacity: Int): CharBuffer {
            if (capacity < 0)
                throw IllegalArgumentException()
            TODO()
//            return HeapCharBuffer(capacity, capacity)
        }

        /**
         * Wraps a char array into a buffer.
         *
         *
         *  The new buffer will be backed by the given char array;
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
         * @return  The new char buffer
         *
         * @throws  IndexOutOfBoundsException
         * If the preconditions on the <tt>offset</tt> and <tt>length</tt>
         * parameters do not hold
         */
//        @JvmOverloads
        fun wrap(array: CharArray,
                 offset: Int = 0, length: Int = array.size): CharBuffer {
//            try {
                TODO()
//                return HeapCharBuffer(array, offset, length)
//            } catch (x: IllegalArgumentException) {
//                throw IndexOutOfBoundsException()
//            }

        }

        /**
         * Wraps a character sequence into a buffer.
         *
         *
         *  The content of the new, read-only buffer will be the content of the
         * given character sequence.  The buffer's capacity will be
         * <tt>csq.length()</tt>, its position will be <tt>start</tt>, its limit
         * will be <tt>end</tt>, and its mark will be undefined.
         *
         * @param  csq
         * The character sequence from which the new character buffer is to
         * be created
         *
         * @param  start
         * The index of the first character to be used;
         * must be non-negative and no larger than <tt>csq.length()</tt>.
         * The new buffer's position will be set to this value.
         *
         * @param  end
         * The index of the character following the last character to be
         * used; must be no smaller than <tt>start</tt> and no larger
         * than <tt>csq.length()</tt>.
         * The new buffer's limit will be set to this value.
         *
         * @return  The new character buffer
         *
         * @throws  IndexOutOfBoundsException
         * If the preconditions on the <tt>start</tt> and <tt>end</tt>
         * parameters do not hold
         */
        fun wrap(csq: CharSequence, start: Int, end: Int): CharBuffer {
            TODO()
//            try {
//                return StringCharBuffer(csq, start, end)
//            } catch (x: IllegalArgumentException) {
//                throw IndexOutOfBoundsException()
//            }

        }

        /**
         * Wraps a character sequence into a buffer.
         *
         *
         *  The content of the new, read-only buffer will be the content of the
         * given character sequence.  The new buffer's capacity and limit will be
         * <tt>csq.length()</tt>, its position will be zero, and its mark will be
         * undefined.
         *
         * @param  csq
         * The character sequence from which the new character buffer is to
         * be created
         *
         * @return  The new character buffer
         */
        fun wrap(csq: CharSequence): CharBuffer {
            return wrap(csq, 0, csq.length)
        }

        private fun equals(x: Char, y: Char): Boolean {


            return x == y

        }

        private fun compare(x: Char, y: Char): Int {


            return x.compareTo(y)

        }
    }


}// Creates a new buffer with the given mark, position, limit, and capacity

//
// package-private
/**
 * Wraps a char array into a buffer.
 *
 *
 *  The new buffer will be backed by the given char array;
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
 * @return  The new char buffer
 */
/**
 * Relative bulk *get* method.
 *
 *
 *  This method transfers chars from this buffer into the given
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
 * If there are fewer than <tt>length</tt> chars
 * remaining in this buffer
 */
/**
 * Relative bulk *put* method&nbsp;&nbsp;*(optional operation)*.
 *
 *
 *  This method transfers the entire content of the given source
 * char array into this buffer.  An invocation of this method of the
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
