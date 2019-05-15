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

import js.lang.StringBuffer
import kotlin.math.min


/**
 * A byte buffer.
 *
 *
 *  This class defines six categories of operations upon
 * byte buffers:
 *
 *
 *
 *  *
 *
 * Absolute and relative [&lt;i&gt;get&lt;/i&gt;][.get] and
 * [&lt;i&gt;put&lt;/i&gt;][.put] methods that read and write
 * single bytes;
 *
 *  *
 *
 * Relative [&lt;i&gt;bulk get&lt;/i&gt;][.get]
 * methods that transfer contiguous sequences of bytes from this buffer
 * into an array;
 *
 *  *
 *
 * Relative [&lt;i&gt;bulk put&lt;/i&gt;][.put]
 * methods that transfer contiguous sequences of bytes from a
 * byte array or some other byte
 * buffer into this buffer;
 *
 *
 *
 *  *
 *
 * Absolute and relative [&lt;i&gt;get&lt;/i&gt;][.getChar]
 * and [&lt;i&gt;put&lt;/i&gt;][.putChar] methods that read and
 * write values of other primitive types, translating them to and from
 * sequences of bytes in a particular byte order;
 *
 *  *
 *
 * Methods for creating *[view buffers](#views)*,
 * which allow a byte buffer to be viewed as a buffer containing values of
 * some other primitive type; and
 *
 *
 *
 *  *
 *
 * Methods for [compacting][.compact], [   ][.duplicate], and [slicing][.slice]
 * a byte buffer.
 *
 *
 *
 *
 *  Byte buffers can be created either by [ &lt;i&gt;allocation&lt;/i&gt;][.allocate], which allocates space for the buffer's
 *
 *
 *
 * content, or by [&lt;i&gt;wrapping&lt;/i&gt;][.wrap] an
 * existing byte array  into a buffer.
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
 * <a name="direct"></a>
 * <h2> Direct *vs.* non-direct buffers </h2>
 *
 *
 *  A byte buffer is either *direct* or *non-direct*.  Given a
 * direct byte buffer, the Java virtual machine will make a best effort to
 * perform native I/O operations directly upon it.  That is, it will attempt to
 * avoid copying the buffer's content to (or from) an intermediate buffer
 * before (or after) each invocation of one of the underlying operating
 * system's native I/O operations.
 *
 *
 *  A direct byte buffer may be created by invoking the [ ][.allocateDirect] factory method of this class.  The
 * buffers returned by this method typically have somewhat higher allocation
 * and deallocation costs than non-direct buffers.  The contents of direct
 * buffers may reside outside of the normal garbage-collected heap, and so
 * their impact upon the memory footprint of an application might not be
 * obvious.  It is therefore recommended that direct buffers be allocated
 * primarily for large, long-lived buffers that are subject to the underlying
 * system's native I/O operations.  In general it is best to allocate direct
 * buffers only when they yield a measureable gain in program performance.
 *
 *
 *  A direct byte buffer may also be created by [ ][java.nio.channels.FileChannel.map] a region of a file
 * directly into memory.  An implementation of the Java platform may optionally
 * support the creation of direct byte buffers from native code via JNI.  If an
 * instance of one of these kinds of buffers refers to an inaccessible region
 * of memory then an attempt to access that region will not change the buffer's
 * content and will cause an unspecified exception to be thrown either at the
 * time of the access or at some later time.
 *
 *
 *  Whether a byte buffer is direct or non-direct may be determined by
 * invoking its [isDirect][.isDirect] method.  This method is provided so
 * that explicit buffer management can be done in performance-critical code.
 *
 *
 * <a name="bin"></a>
 * <h2> Access to binary data </h2>
 *
 *
 *  This class defines methods for reading and writing values of all other
 * primitive types, except <tt>boolean</tt>.  Primitive values are translated
 * to (or from) sequences of bytes according to the buffer's current byte
 * order, which may be retrieved and modified via the [order][.order]
 * methods.  Specific byte orders are represented by instances of the [ ] class.  The initial order of a byte buffer is always [ ][ByteOrder.BIG_ENDIAN].
 *
 *
 *  For access to heterogeneous binary data, that is, sequences of values of
 * different types, this class defines a family of absolute and relative
 * *get* and *put* methods for each type.  For 32-bit floating-point
 * values, for example, this class defines:
 *
 * <blockquote><pre>
 * float  [.getFloat]
 * float  [getFloat(int index)][.getFloat]
 * void  [putFloat(float f)][.putFloat]
 * void  [putFloat(int index, float f)][.putFloat]</pre></blockquote>
 *
 *
 *  Corresponding methods are defined for the types <tt>char</tt>,
 * <tt>short</tt>, <tt>int</tt>, <tt>long</tt>, and <tt>double</tt>.  The index
 * parameters of the absolute *get* and *put* methods are in terms of
 * bytes rather than of the type being read or written.
 *
 * <a name="views"></a>
 *
 *
 *  For access to homogeneous binary data, that is, sequences of values of
 * the same type, this class defines methods that can create *views* of a
 * given byte buffer.  A *view buffer* is simply another buffer whose
 * content is backed by the byte buffer.  Changes to the byte buffer's content
 * will be visible in the view buffer, and vice versa; the two buffers'
 * position, limit, and mark values are independent.  The [ ][.asFloatBuffer] method, for example, creates an instance of
 * the [FloatBuffer] class that is backed by the byte buffer upon which
 * the method is invoked.  Corresponding view-creation methods are defined for
 * the types <tt>char</tt>, <tt>short</tt>, <tt>int</tt>, <tt>long</tt>, and
 * <tt>double</tt>.
 *
 *
 *  View buffers have three important advantages over the families of
 * type-specific *get* and *put* methods described above:
 *
 *
 *
 *  *
 *
 * A view buffer is indexed not in terms of bytes but rather in terms
 * of the type-specific size of its values;
 *
 *  *
 *
 * A view buffer provides relative bulk *get* and *put*
 * methods that can transfer contiguous sequences of values between a buffer
 * and an array or some other buffer of the same type; and
 *
 *  *
 *
 * A view buffer is potentially much more efficient because it will
 * be direct if, and only if, its backing byte buffer is direct.
 *
 *
 *
 *
 *  The byte order of a view buffer is fixed to be that of its byte buffer
 * at the time that the view is created.
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
 * <h2> Invocation chaining </h2>
 *
 *
 *
 *  Methods in this class that do not otherwise have a value to return are
 * specified to return the buffer upon which they are invoked.  This allows
 * method invocations to be chained.
 *
 *
 *
 * The sequence of statements
 *
 * <blockquote><pre>
 * bb.putInt(0xCAFEBABE);
 * bb.putShort(3);
 * bb.putShort(45);</pre></blockquote>
 *
 * can, for example, be replaced by the single statement
 *
 * <blockquote><pre>
 * bb.putInt(0xCAFEBABE).putShort(3).putShort(45);</pre></blockquote>
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
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 */

// Creates a new buffer with the given mark, position, limit, capacity,
// backing array, and array offset
//
abstract class ByteBuffer
internal constructor(mark: Int, pos: Int, lim: Int, cap: Int, // package-private
        // These fields are declared here rather than in Heap-X-Buffer in order to
        // reduce the number of virtual method invocations needed to access these
        // values, which is especially costly when coding small buffers.
        //
                     internal open val hb: ByteArray? = null                  // Non-null only for heap buffers
                     , internal val offset: Int = 0) : Buffer(mark, pos, lim, cap), Comparable<ByteBuffer> {
    override var isReadOnly: Boolean = false                 // Valid only for heap buffers

    /**
     * Tells whether or not this byte buffer is direct.
     *
     * @return  <tt>true</tt> if, and only if, this buffer is direct
     */
    abstract override fun isDirect(): Boolean

    // -- Other char stuff --


    // -- Other byte stuff: Access to binary data --


    internal var bigEndian                                   // package-private
            = true
    internal var nativeByteOrder                             // package-private
            = Bits.byteOrder() === ByteOrder.BIG_ENDIAN


    /**
     * Relative *get* method for reading a char value.
     *
     *
     *  Reads the next two bytes at this buffer's current position,
     * composing them into a char value according to the current byte order,
     * and then increments the position by two.
     *
     * @return  The char value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     * If there are fewer than two bytes
     * remaining in this buffer
     */
    abstract fun getChar(): Char


    /**
     * Relative *get* method for reading a short value.
     *
     *
     *  Reads the next two bytes at this buffer's current position,
     * composing them into a short value according to the current byte order,
     * and then increments the position by two.
     *
     * @return  The short value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     * If there are fewer than two bytes
     * remaining in this buffer
     */
    abstract fun getShort(): Short


    /**
     * Relative *get* method for reading an int value.
     *
     *
     *  Reads the next four bytes at this buffer's current position,
     * composing them into an int value according to the current byte order,
     * and then increments the position by four.
     *
     * @return  The int value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     * If there are fewer than four bytes
     * remaining in this buffer
     */
    abstract fun getInt(): Int


    /**
     * Relative *get* method for reading a long value.
     *
     *
     *  Reads the next eight bytes at this buffer's current position,
     * composing them into a long value according to the current byte order,
     * and then increments the position by eight.
     *
     * @return  The long value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     * If there are fewer than eight bytes
     * remaining in this buffer
     */
    abstract fun getLong(): Long


    /**
     * Relative *get* method for reading a float value.
     *
     *
     *  Reads the next four bytes at this buffer's current position,
     * composing them into a float value according to the current byte order,
     * and then increments the position by four.
     *
     * @return  The float value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     * If there are fewer than four bytes
     * remaining in this buffer
     */
    abstract fun getFloat(): Float


    /**
     * Relative *get* method for reading a double value.
     *
     *
     *  Reads the next eight bytes at this buffer's current position,
     * composing them into a double value according to the current byte order,
     * and then increments the position by eight.
     *
     * @return  The double value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     * If there are fewer than eight bytes
     * remaining in this buffer
     */
    abstract fun getDouble(): Double


    /**
     * Creates a new byte buffer whose content is a shared subsequence of
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
     * will be the number of bytes remaining in this buffer, and its mark
     * will be undefined.  The new buffer will be direct if, and only if, this
     * buffer is direct, and it will be read-only if, and only if, this buffer
     * is read-only.
     *
     * @return  The new byte buffer
     */
    abstract fun slice(): ByteBuffer

    /**
     * Creates a new byte buffer that shares this buffer's content.
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
     * @return  The new byte buffer
     */
    abstract fun duplicate(): ByteBuffer

    /**
     * Creates a new, read-only byte buffer that shares this buffer's
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
     * @return  The new, read-only byte buffer
     */
    abstract fun asReadOnlyBuffer(): ByteBuffer


    // -- Singleton get/put methods --

    /**
     * Relative *get* method.  Reads the byte at this buffer's
     * current position, and then increments the position.
     *
     * @return  The byte at the buffer's current position
     *
     * @throws  BufferUnderflowException
     * If the buffer's current position is not smaller than its limit
     */
    abstract fun get(): Byte

    /**
     * Relative *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes the given byte into this buffer at the current
     * position, and then increments the position.
     *
     * @param  b
     * The byte to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If this buffer's current position is not smaller than its limit
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun put(b: Byte): ByteBuffer

    /**
     * Absolute *get* method.  Reads the byte at the given
     * index.
     *
     * @param  index
     * The index from which the byte will be read
     *
     * @return  The byte at the given index
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit
     */
    abstract operator fun get(index: Int): Byte


    /**
     * Absolute *put* method&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes the given byte into this buffer at the given
     * index.
     *
     * @param  index
     * The index at which the byte will be written
     *
     * @param  b
     * The byte value to be written
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
    abstract fun put(index: Int, b: Byte): ByteBuffer


    // -- Bulk get operations --

    /**
     * Relative bulk *get* method.
     *
     *
     *  This method transfers bytes from this buffer into the given
     * destination array.  If there are fewer bytes remaining in the
     * buffer than are required to satisfy the request, that is, if
     * <tt>length</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>, then no
     * bytes are transferred and a [BufferUnderflowException] is
     * thrown.
     *
     *
     *  Otherwise, this method copies <tt>length</tt> bytes from this
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
     * except that it first checks that there are sufficient bytes in
     * this buffer and it is potentially much more efficient.
     *
     * @param  dst
     * The array into which bytes are to be written
     *
     * @param  offset
     * The offset within the array of the first byte to be
     * written; must be non-negative and no larger than
     * <tt>dst.length</tt>
     *
     * @param  length
     * The maximum number of bytes to be written to the given
     * array; must be non-negative and no larger than
     * <tt>dst.length - offset</tt>
     *
     * @return  This buffer
     *
     * @throws  BufferUnderflowException
     * If there are fewer than <tt>length</tt> bytes
     * remaining in this buffer
     *
     * @throws  IndexOutOfBoundsException
     * If the preconditions on the <tt>offset</tt> and <tt>length</tt>
     * parameters do not hold
     */
//    @JvmOverloads
    open fun get(dst: ByteArray, offset: Int = 0, length: Int = dst.size): ByteBuffer {
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
     *  This method transfers the bytes remaining in the given source
     * buffer into this buffer.  If there are more bytes remaining in the
     * source buffer than in this buffer, that is, if
     * <tt>src.remaining()</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>,
     * then no bytes are transferred and a [ ] is thrown.
     *
     *
     *  Otherwise, this method copies
     * *n*&nbsp;=&nbsp;<tt>src.remaining()</tt> bytes from the given
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
     * The source buffer from which bytes are to be read;
     * must not be this buffer
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there is insufficient space in this buffer
     * for the remaining bytes in the source buffer
     *
     * @throws  IllegalArgumentException
     * If the source buffer is this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    open fun put(src: ByteBuffer): ByteBuffer {
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
     *  This method transfers bytes into this buffer from the given
     * source array.  If there are more bytes to be copied from the array
     * than remain in this buffer, that is, if
     * <tt>length</tt>&nbsp;<tt>&gt;</tt>&nbsp;<tt>remaining()</tt>, then no
     * bytes are transferred and a [BufferOverflowException] is
     * thrown.
     *
     *
     *  Otherwise, this method copies <tt>length</tt> bytes from the
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
     * The array from which bytes are to be read
     *
     * @param  offset
     * The offset within the array of the first byte to be read;
     * must be non-negative and no larger than <tt>array.length</tt>
     *
     * @param  length
     * The number of bytes to be read from the given array;
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
    open fun put(src: ByteArray, offset: Int = 0, length: Int = src.size): ByteBuffer {
        checkBounds(offset, length, src.size)
        if (length > remaining())
            throw BufferOverflowException()
        val end = offset + length
        for (i in offset until end)
            this.put(src[i])
        return this
    }


    // -- Other stuff --

    /**
     * Tells whether or not this buffer is backed by an accessible byte
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
     * Returns the byte array that backs this
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
    override fun array(): ByteArray {
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
     *  The bytes between the buffer's current position and its limit,
     * if any, are copied to the beginning of the buffer.  That is, the
     * byte at index *p*&nbsp;=&nbsp;<tt>position()</tt> is copied
     * to index zero, the byte at index *p*&nbsp;+&nbsp;1 is copied
     * to index one, and so forth until the byte at index
     * <tt>limit()</tt>&nbsp;-&nbsp;1 is copied to index
     * *n*&nbsp;=&nbsp;<tt>limit()</tt>&nbsp;-&nbsp;<tt>1</tt>&nbsp;-&nbsp;*p*.
     * The buffer's position is then set to *n+1* and its limit is set to
     * its capacity.  The mark, if defined, is discarded.
     *
     *
     *  The buffer's position is set to the number of bytes copied,
     * rather than to zero, so that an invocation of this method can be
     * followed immediately by an invocation of another relative *put*
     * method.
     *
     *
     *
     *
     *  Invoke this method after writing data from a buffer in case the
     * write was incomplete.  The following loop, for example, copies bytes
     * from one channel to another via the buffer <tt>buf</tt>:
     *
     * <blockquote><pre>`buf.clear();          // Prepare buffer for use
     * while (in.read(buf) >= 0 || buf.position != 0) {
     * buf.flip();
     * out.write(buf);
     * buf.compact();    // In case of partial write
     * }
    `</pre></blockquote> *
     *
     *
     *
     * @return  This buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun compact(): ByteBuffer


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
     *  The hash code of a byte buffer depends only upon its remaining
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
     *  Two byte buffers are equal if, and only if,
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
     *  A byte buffer is not equal to any other type of object.
     *
     * @param  ob  The object to which this buffer is to be compared
     *
     * @return  <tt>true</tt> if, and only if, this buffer is equal to the
     * given object
     */
    override fun equals(ob: Any?): Boolean {
        if (this === ob)
            return true
        if (ob !is ByteBuffer)
            return false
        val that = ob as ByteBuffer
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
     *  Two byte buffers are compared by comparing their sequences of
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
     * Pairs of `byte` elements are compared as if by invoking
     * [Byte.compare].
     *
     *
     *
     *  A byte buffer is not comparable to any other type of object.
     *
     * @return  A negative integer, zero, or a positive integer as this buffer
     * is less than, equal to, or greater than the given buffer
     */
    override fun compareTo(that: ByteBuffer): Int {
        val n = this.position() + min(this.remaining(), that.remaining())
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

    /**
     * Retrieves this buffer's byte order.
     *
     *
     *  The byte order is used when reading or writing multibyte values, and
     * when creating buffers that are views of this byte buffer.  The order of
     * a newly-created byte buffer is always [ BIG_ENDIAN][ByteOrder.BIG_ENDIAN].
     *
     * @return  This buffer's byte order
     */
    fun order(): ByteOrder {
        return if (bigEndian) ByteOrder.BIG_ENDIAN else ByteOrder.LITTLE_ENDIAN
    }

    /**
     * Modifies this buffer's byte order.
     *
     * @param  bo
     * The new byte order,
     * either [BIG_ENDIAN][ByteOrder.BIG_ENDIAN]
     * or [LITTLE_ENDIAN][ByteOrder.LITTLE_ENDIAN]
     *
     * @return  This buffer
     */
    fun order(bo: ByteOrder): ByteBuffer {
        bigEndian = bo === ByteOrder.BIG_ENDIAN
        nativeByteOrder = bigEndian == (Bits.byteOrder() === ByteOrder.BIG_ENDIAN)
        return this
    }

    // Unchecked accessors, for use by ByteBufferAs-X-Buffer classes
    //
    internal abstract fun _get(i: Int): Byte                           // package-private

    internal abstract fun _put(i: Int, b: Byte)                   // package-private

    /**
     * Relative *put* method for writing a char
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes two bytes containing the given char value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by two.
     *
     * @param  value
     * The char value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there are fewer than two bytes
     * remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putChar(value: Char): ByteBuffer

    /**
     * Absolute *get* method for reading a char value.
     *
     *
     *  Reads two bytes at the given index, composing them into a
     * char value according to the current byte order.
     *
     * @param  index
     * The index from which the bytes will be read
     *
     * @return  The char value at the given index
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus one
     */
    abstract fun getChar(index: Int): Char

    /**
     * Absolute *put* method for writing a char
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes two bytes containing the given char value, in the
     * current byte order, into this buffer at the given index.
     *
     * @param  index
     * The index at which the bytes will be written
     *
     * @param  value
     * The char value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus one
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putChar(index: Int, value: Char): ByteBuffer

    /**
     * Creates a view of this byte buffer as a char buffer.
     *
     *
     *  The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     *
     *  The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * two, and its mark will be undefined.  The new buffer will be direct
     * if, and only if, this buffer is direct, and it will be read-only if, and
     * only if, this buffer is read-only.
     *
     * @return  A new char buffer
     */
    abstract fun asCharBuffer(): CharBuffer

    /**
     * Relative *put* method for writing a short
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes two bytes containing the given short value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by two.
     *
     * @param  value
     * The short value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there are fewer than two bytes
     * remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putShort(value: Short): ByteBuffer

    /**
     * Absolute *get* method for reading a short value.
     *
     *
     *  Reads two bytes at the given index, composing them into a
     * short value according to the current byte order.
     *
     * @param  index
     * The index from which the bytes will be read
     *
     * @return  The short value at the given index
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus one
     */
    abstract fun getShort(index: Int): Short

    /**
     * Absolute *put* method for writing a short
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes two bytes containing the given short value, in the
     * current byte order, into this buffer at the given index.
     *
     * @param  index
     * The index at which the bytes will be written
     *
     * @param  value
     * The short value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus one
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putShort(index: Int, value: Short): ByteBuffer

    /**
     * Creates a view of this byte buffer as a short buffer.
     *
     *
     *  The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     *
     *  The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * two, and its mark will be undefined.  The new buffer will be direct
     * if, and only if, this buffer is direct, and it will be read-only if, and
     * only if, this buffer is read-only.
     *
     * @return  A new short buffer
     */
    abstract fun asShortBuffer(): ShortBuffer

    /**
     * Relative *put* method for writing an int
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes four bytes containing the given int value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by four.
     *
     * @param  value
     * The int value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there are fewer than four bytes
     * remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putInt(value: Int): ByteBuffer

    /**
     * Absolute *get* method for reading an int value.
     *
     *
     *  Reads four bytes at the given index, composing them into a
     * int value according to the current byte order.
     *
     * @param  index
     * The index from which the bytes will be read
     *
     * @return  The int value at the given index
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus three
     */
    abstract fun getInt(index: Int): Int

    /**
     * Absolute *put* method for writing an int
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes four bytes containing the given int value, in the
     * current byte order, into this buffer at the given index.
     *
     * @param  index
     * The index at which the bytes will be written
     *
     * @param  value
     * The int value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus three
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putInt(index: Int, value: Int): ByteBuffer

    /**
     * Creates a view of this byte buffer as an int buffer.
     *
     *
     *  The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     *
     *  The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * four, and its mark will be undefined.  The new buffer will be direct
     * if, and only if, this buffer is direct, and it will be read-only if, and
     * only if, this buffer is read-only.
     *
     * @return  A new int buffer
     */
    abstract fun asIntBuffer(): IntBuffer

    /**
     * Relative *put* method for writing a long
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes eight bytes containing the given long value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by eight.
     *
     * @param  value
     * The long value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there are fewer than eight bytes
     * remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putLong(value: Long): ByteBuffer

    /**
     * Absolute *get* method for reading a long value.
     *
     *
     *  Reads eight bytes at the given index, composing them into a
     * long value according to the current byte order.
     *
     * @param  index
     * The index from which the bytes will be read
     *
     * @return  The long value at the given index
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus seven
     */
    abstract fun getLong(index: Int): Long

    /**
     * Absolute *put* method for writing a long
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes eight bytes containing the given long value, in the
     * current byte order, into this buffer at the given index.
     *
     * @param  index
     * The index at which the bytes will be written
     *
     * @param  value
     * The long value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus seven
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putLong(index: Int, value: Long): ByteBuffer

    /**
     * Creates a view of this byte buffer as a long buffer.
     *
     *
     *  The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     *
     *  The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * eight, and its mark will be undefined.  The new buffer will be direct
     * if, and only if, this buffer is direct, and it will be read-only if, and
     * only if, this buffer is read-only.
     *
     * @return  A new long buffer
     */
    abstract fun asLongBuffer(): LongBuffer

    /**
     * Relative *put* method for writing a float
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes four bytes containing the given float value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by four.
     *
     * @param  value
     * The float value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there are fewer than four bytes
     * remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putFloat(value: Float): ByteBuffer

    /**
     * Absolute *get* method for reading a float value.
     *
     *
     *  Reads four bytes at the given index, composing them into a
     * float value according to the current byte order.
     *
     * @param  index
     * The index from which the bytes will be read
     *
     * @return  The float value at the given index
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus three
     */
    abstract fun getFloat(index: Int): Float

    /**
     * Absolute *put* method for writing a float
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes four bytes containing the given float value, in the
     * current byte order, into this buffer at the given index.
     *
     * @param  index
     * The index at which the bytes will be written
     *
     * @param  value
     * The float value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus three
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putFloat(index: Int, value: Float): ByteBuffer

    /**
     * Creates a view of this byte buffer as a float buffer.
     *
     *
     *  The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     *
     *  The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * four, and its mark will be undefined.  The new buffer will be direct
     * if, and only if, this buffer is direct, and it will be read-only if, and
     * only if, this buffer is read-only.
     *
     * @return  A new float buffer
     */
    abstract fun asFloatBuffer(): FloatBuffer

    /**
     * Relative *put* method for writing a double
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes eight bytes containing the given double value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by eight.
     *
     * @param  value
     * The double value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     * If there are fewer than eight bytes
     * remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putDouble(value: Double): ByteBuffer

    /**
     * Absolute *get* method for reading a double value.
     *
     *
     *  Reads eight bytes at the given index, composing them into a
     * double value according to the current byte order.
     *
     * @param  index
     * The index from which the bytes will be read
     *
     * @return  The double value at the given index
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus seven
     */
    abstract fun getDouble(index: Int): Double

    /**
     * Absolute *put* method for writing a double
     * value&nbsp;&nbsp;*(optional operation)*.
     *
     *
     *  Writes eight bytes containing the given double value, in the
     * current byte order, into this buffer at the given index.
     *
     * @param  index
     * The index at which the bytes will be written
     *
     * @param  value
     * The double value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     * If <tt>index</tt> is negative
     * or not smaller than the buffer's limit,
     * minus seven
     *
     * @throws  ReadOnlyBufferException
     * If this buffer is read-only
     */
    abstract fun putDouble(index: Int, value: Double): ByteBuffer

    /**
     * Creates a view of this byte buffer as a double buffer.
     *
     *
     *  The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     *
     *  The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * eight, and its mark will be undefined.  The new buffer will be direct
     * if, and only if, this buffer is direct, and it will be read-only if, and
     * only if, this buffer is read-only.
     *
     * @return  A new double buffer
     */
    abstract fun asDoubleBuffer(): DoubleBuffer

    companion object {


        /**
         * Allocates a new direct byte buffer.
         *
         *
         *  The new buffer's position will be zero, its limit will be its
         * capacity, its mark will be undefined, and each of its elements will be
         * initialized to zero.  Whether or not it has a
         * [backing array][.hasArray] is unspecified.
         *
         * @param  capacity
         * The new buffer's capacity, in bytes
         *
         * @return  The new byte buffer
         *
         * @throws  IllegalArgumentException
         * If the <tt>capacity</tt> is a negative integer
         */
        fun allocateDirect(capacity: Int): ByteBuffer {
            TODO("allocateDirect not implemented")
//            return DirectByteBuffer(capacity)
        }


        /**
         * Allocates a new byte buffer.
         *
         *
         *  The new buffer's position will be zero, its limit will be its
         * capacity, its mark will be undefined, and each of its elements will be
         * initialized to zero.  It will have a [backing array][.array],
         * and its [array offset][.arrayOffset] will be zero.
         *
         * @param  capacity
         * The new buffer's capacity, in bytes
         *
         * @return  The new byte buffer
         *
         * @throws  IllegalArgumentException
         * If the <tt>capacity</tt> is a negative integer
         */
        fun allocate(capacity: Int): ByteBuffer {
            if (capacity < 0)
                throw IllegalArgumentException()
            return HeapByteBuffer(capacity, capacity)
        }

        /**
         * Wraps a byte array into a buffer.
         *
         *
         *  The new buffer will be backed by the given byte array;
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
         * @return  The new byte buffer
         *
         * @throws  IndexOutOfBoundsException
         * If the preconditions on the <tt>offset</tt> and <tt>length</tt>
         * parameters do not hold
         */
//        @JvmOverloads
        fun wrap(array: ByteArray,
                 offset: Int = 0, length: Int = array.size): ByteBuffer {
            try {
                return HeapByteBuffer(array, offset, length)
            } catch (x: IllegalArgumentException) {
                throw IndexOutOfBoundsException()
            }

        }

        private fun equals(x: Byte, y: Byte): Boolean {


            return x == y

        }

        private fun compare(x: Byte, y: Byte): Int {
            return x.compareTo(y)

        }
    }

}// Creates a new buffer with the given mark, position, limit, and capacity
//
// package-private
/**
 * Wraps a byte array into a buffer.
 *
 *
 *  The new buffer will be backed by the given byte array;
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
 * @return  The new byte buffer
 */
/**
 * Relative bulk *get* method.
 *
 *
 *  This method transfers bytes from this buffer into the given
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
 * If there are fewer than <tt>length</tt> bytes
 * remaining in this buffer
 */
/**
 * Relative bulk *put* method&nbsp;&nbsp;*(optional operation)*.
 *
 *
 *  This method transfers the entire content of the given source
 * byte array into this buffer.  An invocation of this method of the
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
