/*
 * Copyright (c) 2000, 2001, Oracle and/or its affiliates. All rights reserved.
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

package js.nio.channels

import js.io.IOException
import js.nio.ByteBuffer


/**
 * A channel that can read bytes.
 *
 *
 *  Only one read operation upon a readable channel may be in progress at
 * any given time.  If one thread initiates a read operation upon a channel
 * then any other thread that attempts to initiate another read operation will
 * block until the first operation is complete.  Whether or not other kinds of
 * I/O operations may proceed concurrently with a read operation depends upon
 * the type of the channel.
 *
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 */

interface ReadableByteChannel : Channel {

    /**
     * Reads a sequence of bytes from this channel into the given buffer.
     *
     *
     *  An attempt is made to read up to *r* bytes from the channel,
     * where *r* is the number of bytes remaining in the buffer, that is,
     * <tt>dst.remaining()</tt>, at the moment this method is invoked.
     *
     *
     *  Suppose that a byte sequence of length *n* is read, where
     * <tt>0</tt>&nbsp;<tt>&lt;=</tt>&nbsp;*n*&nbsp;<tt>&lt;=</tt>&nbsp;*r*.
     * This byte sequence will be transferred into the buffer so that the first
     * byte in the sequence is at index *p* and the last byte is at index
     * *p*&nbsp;<tt>+</tt>&nbsp;*n*&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>,
     * where *p* is the buffer's position at the moment this method is
     * invoked.  Upon return the buffer's position will be equal to
     * *p*&nbsp;<tt>+</tt>&nbsp;*n*; its limit will not have changed.
     *
     *
     *  A read operation might not fill the buffer, and in fact it might not
     * read any bytes at all.  Whether or not it does so depends upon the
     * nature and state of the channel.  A socket channel in non-blocking mode,
     * for example, cannot read any more bytes than are immediately available
     * from the socket's input buffer; similarly, a file channel cannot read
     * any more bytes than remain in the file.  It is guaranteed, however, that
     * if a channel is in blocking mode and there is at least one byte
     * remaining in the buffer then this method will block until at least one
     * byte is read.
     *
     *
     *  This method may be invoked at any time.  If another thread has
     * already initiated a read operation upon this channel, however, then an
     * invocation of this method will block until the first operation is
     * complete.
     *
     * @param  dst
     * The buffer into which bytes are to be transferred
     *
     * @return  The number of bytes read, possibly zero, or <tt>-1</tt> if the
     * channel has reached end-of-stream
     *
     * @throws  NonReadableChannelException
     * If this channel was not opened for reading
     *
     * @throws  ClosedChannelException
     * If this channel is closed
     *
     * @throws  AsynchronousCloseException
     * If another thread closes this channel
     * while the read operation is in progress
     *
     * @throws  ClosedByInterruptException
     * If another thread interrupts the current thread
     * while the read operation is in progress, thereby
     * closing the channel and setting the current thread's
     * interrupt status
     *
     * @throws  IOException
     * If some other I/O error occurs
     */
//    @Throws(IOException::class)
    fun read(dst: ByteBuffer): Int

}
