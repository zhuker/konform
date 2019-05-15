/*
 * Copyright (c) 2000, 2016, Oracle and/or its affiliates. All rights reserved.
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

package js.nio

import js.lang.*
import js.util.concurrent.AtomicLong

//import java.security.AccessController
//import java.util.concurrent.atomic.AtomicLong
//import java.util.concurrent.atomic.LongAdder
//
//import sun.misc.JavaLangRefAccess
//import sun.misc.SharedSecrets
//import sun.misc.Unsafe
//import sun.misc.VM

/**
 * Access to bits, native and otherwise.
 */

internal object Bits {                            // package-private


    // -- Unsafe access --

    private val unsafe = Unsafe.getUnsafe()


    // -- Processor and memory-system properties --

    private val byteOrder: ByteOrder = Platform.byteorder()


    private var pageSize = -1

    private var unaligned: Boolean = false
    private var unalignedKnown = false


    // -- Direct memory management --

    // A user-settable upper limit on the maximum amount of allocatable
    // direct buffer memory.  This value may be changed during VM
    // initialization if it is launched with "-XX:MaxDirectMemorySize=<size>".
//    @Volatile
    private var maxMemory = VM.maxDirectMemory()
    private val reservedMemory = AtomicLong()
    private val totalCapacity = AtomicLong()
    private val count = AtomicLong()
    //    @Volatile
    private var memoryLimitSet = false
    // max. number of sleeps during try-reserving with exponentially
    // increasing delay before throwing OutOfMemoryError:
    // 1, 2, 4, 8, 16, 32, 64, 128, 256 (total 511 ms ~ 0.5 s)
    // which means that OOME will be thrown after 0.5 s of trying
    private val MAX_SLEEPS = 9

    // -- Bulk get/put acceleration --

    // These numbers represent the point at which we have empirically
    // determined that the average cost of a JNI call exceeds the expense
    // of an element by element copy.  These numbers may change over time.
    val JNI_COPY_TO_ARRAY_THRESHOLD = 6
    val JNI_COPY_FROM_ARRAY_THRESHOLD = 6

    // This number limits the number of bytes to copy per call to Unsafe's
    // copyMemory method. A limit is imposed to allow for safepoint polling
    // during a large copy
    val UNSAFE_COPY_THRESHOLD = 1024L * 1024L


    // -- Swapping --

    fun swap(x: Short): Short {
        return Short.reverseBytes(x)
    }

    fun swap(x: Char): Char {
        return Char.reverseBytes(x)
    }

    fun swap(x: Int): Int {
        return Int.reverseBytes(x)
    }

    fun swap(x: Long): Long {
        return Long.reverseBytes(x)
    }


    // -- get/put char --

    private fun makeChar(b1: Byte, b0: Byte): Char {
        return (b1 shl 8 or (b0 and 0xff)).toChar()
    }

    fun getCharL(bb: ByteBuffer, bi: Int): Char {
        return makeChar(bb._get(bi + 1),
                bb._get(bi))
    }

    fun getCharL(a: Long): Char {
        return makeChar(_get(a + 1),
                _get(a))
    }

    fun getCharB(bb: ByteBuffer, bi: Int): Char {
        return makeChar(bb._get(bi),
                bb._get(bi + 1))
    }

    fun getCharB(a: Long): Char {
        return makeChar(_get(a),
                _get(a + 1))
    }

    fun getChar(bb: ByteBuffer, bi: Int, bigEndian: Boolean): Char {
        return if (bigEndian) getCharB(bb, bi) else getCharL(bb, bi)
    }

    fun getChar(a: Long, bigEndian: Boolean): Char {
        return if (bigEndian) getCharB(a) else getCharL(a)
    }

    private fun char1(x: Char): Byte {
        return (x.toInt() shr 8).toByte()
    }

    private fun char0(x: Char): Byte {
        return x.toByte()
    }

    fun putCharL(bb: ByteBuffer, bi: Int, x: Char) {
        bb._put(bi, char0(x))
        bb._put(bi + 1, char1(x))
    }

    fun putCharL(a: Long, x: Char) {
        _put(a, char0(x))
        _put(a + 1, char1(x))
    }

    fun putCharB(bb: ByteBuffer, bi: Int, x: Char) {
        bb._put(bi, char1(x))
        bb._put(bi + 1, char0(x))
    }

    fun putCharB(a: Long, x: Char) {
        _put(a, char1(x))
        _put(a + 1, char0(x))
    }

    fun putChar(bb: ByteBuffer, bi: Int, x: Char, bigEndian: Boolean) {
        if (bigEndian)
            putCharB(bb, bi, x)
        else
            putCharL(bb, bi, x)
    }

    fun putChar(a: Long, x: Char, bigEndian: Boolean) {
        if (bigEndian)
            putCharB(a, x)
        else
            putCharL(a, x)
    }


    // -- get/put short --

    private fun makeShort(b1: Byte, b0: Byte): Short {
        return (b1 shl 8 or (b0 and 0xff)).toShort()
    }

    fun getShortL(bb: ByteBuffer, bi: Int): Short {
        return makeShort(bb._get(bi + 1),
                bb._get(bi))
    }

    fun getShortL(a: Long): Short {
        return makeShort(_get(a + 1),
                _get(a))
    }

    fun getShortB(bb: ByteBuffer, bi: Int): Short {
        return makeShort(bb._get(bi),
                bb._get(bi + 1))
    }

    fun getShortB(a: Long): Short {
        return makeShort(_get(a),
                _get(a + 1))
    }

    fun getShort(bb: ByteBuffer, bi: Int, bigEndian: Boolean): Short {
        return if (bigEndian) getShortB(bb, bi) else getShortL(bb, bi)
    }

    fun getShort(a: Long, bigEndian: Boolean): Short {
        return if (bigEndian) getShortB(a) else getShortL(a)
    }

    private fun short1(x: Short): Byte {
        return (x shr 8).toByte()
    }

    private fun short0(x: Short): Byte {
        return x.toByte()
    }

    fun putShortL(bb: ByteBuffer, bi: Int, x: Short) {
        bb._put(bi, short0(x))
        bb._put(bi + 1, short1(x))
    }

    fun putShortL(a: Long, x: Short) {
        _put(a, short0(x))
        _put(a + 1, short1(x))
    }

    fun putShortB(bb: ByteBuffer, bi: Int, x: Short) {
        bb._put(bi, short1(x))
        bb._put(bi + 1, short0(x))
    }

    fun putShortB(a: Long, x: Short) {
        _put(a, short1(x))
        _put(a + 1, short0(x))
    }

    fun putShort(bb: ByteBuffer, bi: Int, x: Short, bigEndian: Boolean) {
        if (bigEndian)
            putShortB(bb, bi, x)
        else
            putShortL(bb, bi, x)
    }

    fun putShort(a: Long, x: Short, bigEndian: Boolean) {
        if (bigEndian)
            putShortB(a, x)
        else
            putShortL(a, x)
    }


    // -- get/put int --

    private fun makeInt(b3: Byte, b2: Byte, b1: Byte, b0: Byte): Int {
        return b3 shl 24 or
                (b2 and 0xff shl 16) or
                (b1 and 0xff shl 8) or
                (b0 and 0xff)
    }

    fun getIntL(bb: ByteBuffer, bi: Int): Int {
        return makeInt(bb._get(bi + 3),
                bb._get(bi + 2),
                bb._get(bi + 1),
                bb._get(bi))
    }

    fun getIntL(a: Long): Int {
        return makeInt(_get(a + 3),
                _get(a + 2),
                _get(a + 1),
                _get(a))
    }

    fun getIntB(bb: ByteBuffer, bi: Int): Int {
        return makeInt(bb._get(bi),
                bb._get(bi + 1),
                bb._get(bi + 2),
                bb._get(bi + 3))
    }

    fun getIntB(a: Long): Int {
        return makeInt(_get(a),
                _get(a + 1),
                _get(a + 2),
                _get(a + 3))
    }

    fun getInt(bb: ByteBuffer, bi: Int, bigEndian: Boolean): Int {
        return if (bigEndian) getIntB(bb, bi) else getIntL(bb, bi)
    }

    fun getInt(a: Long, bigEndian: Boolean): Int {
        return if (bigEndian) getIntB(a) else getIntL(a)
    }

    private fun int3(x: Int): Byte {
        return (x shr 24).toByte()
    }

    private fun int2(x: Int): Byte {
        return (x shr 16).toByte()
    }

    private fun int1(x: Int): Byte {
        return (x shr 8).toByte()
    }

    private fun int0(x: Int): Byte {
        return x.toByte()
    }

    fun putIntL(bb: ByteBuffer, bi: Int, x: Int) {
        bb._put(bi + 3, int3(x))
        bb._put(bi + 2, int2(x))
        bb._put(bi + 1, int1(x))
        bb._put(bi, int0(x))
    }

    fun putIntL(a: Long, x: Int) {
        _put(a + 3, int3(x))
        _put(a + 2, int2(x))
        _put(a + 1, int1(x))
        _put(a, int0(x))
    }

    fun putIntB(bb: ByteBuffer, bi: Int, x: Int) {
        bb._put(bi, int3(x))
        bb._put(bi + 1, int2(x))
        bb._put(bi + 2, int1(x))
        bb._put(bi + 3, int0(x))
    }

    fun putIntB(a: Long, x: Int) {
        _put(a, int3(x))
        _put(a + 1, int2(x))
        _put(a + 2, int1(x))
        _put(a + 3, int0(x))
    }

    fun putInt(bb: ByteBuffer, bi: Int, x: Int, bigEndian: Boolean) {
        if (bigEndian)
            putIntB(bb, bi, x)
        else
            putIntL(bb, bi, x)
    }

    fun putInt(a: Long, x: Int, bigEndian: Boolean) {
        if (bigEndian)
            putIntB(a, x)
        else
            putIntL(a, x)
    }


    // -- get/put long --

    private fun makeLong(b7: Byte, b6: Byte, b5: Byte, b4: Byte,
                         b3: Byte, b2: Byte, b1: Byte, b0: Byte): Long {
        return b7.toLong() shl 56 or
                (b6.toLong() and 0xff shl 48) or
                (b5.toLong() and 0xff shl 40) or
                (b4.toLong() and 0xff shl 32) or
                (b3.toLong() and 0xff shl 24) or
                (b2.toLong() and 0xff shl 16) or
                (b1.toLong() and 0xff shl 8) or
                (b0.toLong() and 0xff)
    }

    fun getLongL(bb: ByteBuffer, bi: Int): Long {
        return makeLong(bb._get(bi + 7),
                bb._get(bi + 6),
                bb._get(bi + 5),
                bb._get(bi + 4),
                bb._get(bi + 3),
                bb._get(bi + 2),
                bb._get(bi + 1),
                bb._get(bi))
    }

    fun getLongL(a: Long): Long {
        return makeLong(_get(a + 7),
                _get(a + 6),
                _get(a + 5),
                _get(a + 4),
                _get(a + 3),
                _get(a + 2),
                _get(a + 1),
                _get(a))
    }

    fun getLongB(bb: ByteBuffer, bi: Int): Long {
        return makeLong(bb._get(bi),
                bb._get(bi + 1),
                bb._get(bi + 2),
                bb._get(bi + 3),
                bb._get(bi + 4),
                bb._get(bi + 5),
                bb._get(bi + 6),
                bb._get(bi + 7))
    }

    fun getLongB(a: Long): Long {
        return makeLong(_get(a),
                _get(a + 1),
                _get(a + 2),
                _get(a + 3),
                _get(a + 4),
                _get(a + 5),
                _get(a + 6),
                _get(a + 7))
    }

    fun getLong(bb: ByteBuffer, bi: Int, bigEndian: Boolean): Long {
        return if (bigEndian) getLongB(bb, bi) else getLongL(bb, bi)
    }

    fun getLong(a: Long, bigEndian: Boolean): Long {
        return if (bigEndian) getLongB(a) else getLongL(a)
    }

    private fun long7(x: Long): Byte {
        return (x shr 56).toByte()
    }

    private fun long6(x: Long): Byte {
        return (x shr 48).toByte()
    }

    private fun long5(x: Long): Byte {
        return (x shr 40).toByte()
    }

    private fun long4(x: Long): Byte {
        return (x shr 32).toByte()
    }

    private fun long3(x: Long): Byte {
        return (x shr 24).toByte()
    }

    private fun long2(x: Long): Byte {
        return (x shr 16).toByte()
    }

    private fun long1(x: Long): Byte {
        return (x shr 8).toByte()
    }

    private fun long0(x: Long): Byte {
        return x.toByte()
    }

    fun putLongL(bb: ByteBuffer, bi: Int, x: Long) {
        bb._put(bi + 7, long7(x))
        bb._put(bi + 6, long6(x))
        bb._put(bi + 5, long5(x))
        bb._put(bi + 4, long4(x))
        bb._put(bi + 3, long3(x))
        bb._put(bi + 2, long2(x))
        bb._put(bi + 1, long1(x))
        bb._put(bi, long0(x))
    }

    fun putLongL(a: Long, x: Long) {
        _put(a + 7, long7(x))
        _put(a + 6, long6(x))
        _put(a + 5, long5(x))
        _put(a + 4, long4(x))
        _put(a + 3, long3(x))
        _put(a + 2, long2(x))
        _put(a + 1, long1(x))
        _put(a, long0(x))
    }

    fun putLongB(bb: ByteBuffer, bi: Int, x: Long) {
        bb._put(bi, long7(x))
        bb._put(bi + 1, long6(x))
        bb._put(bi + 2, long5(x))
        bb._put(bi + 3, long4(x))
        bb._put(bi + 4, long3(x))
        bb._put(bi + 5, long2(x))
        bb._put(bi + 6, long1(x))
        bb._put(bi + 7, long0(x))
    }

    fun putLongB(a: Long, x: Long) {
        _put(a, long7(x))
        _put(a + 1, long6(x))
        _put(a + 2, long5(x))
        _put(a + 3, long4(x))
        _put(a + 4, long3(x))
        _put(a + 5, long2(x))
        _put(a + 6, long1(x))
        _put(a + 7, long0(x))
    }

    fun putLong(bb: ByteBuffer, bi: Int, x: Long, bigEndian: Boolean) {
        if (bigEndian)
            putLongB(bb, bi, x)
        else
            putLongL(bb, bi, x)
    }

    fun putLong(a: Long, x: Long, bigEndian: Boolean) {
        if (bigEndian)
            putLongB(a, x)
        else
            putLongL(a, x)
    }


    // -- get/put float --

    fun getFloatL(bb: ByteBuffer, bi: Int): Float {
        return Float.intBitsToFloat(getIntL(bb, bi))
    }

    fun getFloatL(a: Long): Float {
        return Float.intBitsToFloat(getIntL(a))
    }

    fun getFloatB(bb: ByteBuffer, bi: Int): Float {
        return Float.intBitsToFloat(getIntB(bb, bi))
    }

    fun getFloatB(a: Long): Float {
        return Float.intBitsToFloat(getIntB(a))
    }

    fun getFloat(bb: ByteBuffer, bi: Int, bigEndian: Boolean): Float {
        return if (bigEndian) getFloatB(bb, bi) else getFloatL(bb, bi)
    }

    fun getFloat(a: Long, bigEndian: Boolean): Float {
        return if (bigEndian) getFloatB(a) else getFloatL(a)
    }

    fun putFloatL(bb: ByteBuffer, bi: Int, x: Float) {
        putIntL(bb, bi, Float.floatToRawIntBits(x))
    }

    fun putFloatL(a: Long, x: Float) {
        putIntL(a, Float.floatToRawIntBits(x))
    }

    fun putFloatB(bb: ByteBuffer, bi: Int, x: Float) {
        putIntB(bb, bi, Float.floatToRawIntBits(x))
    }

    fun putFloatB(a: Long, x: Float) {
        putIntB(a, Float.floatToRawIntBits(x))
    }

    fun putFloat(bb: ByteBuffer, bi: Int, x: Float, bigEndian: Boolean) {
        if (bigEndian)
            putFloatB(bb, bi, x)
        else
            putFloatL(bb, bi, x)
    }

    fun putFloat(a: Long, x: Float, bigEndian: Boolean) {
        if (bigEndian)
            putFloatB(a, x)
        else
            putFloatL(a, x)
    }


    // -- get/put double --

    fun getDoubleL(bb: ByteBuffer, bi: Int): Double {
        return Double.longBitsToDouble(getLongL(bb, bi))
    }

    fun getDoubleL(a: Long): Double {
        return Double.longBitsToDouble(getLongL(a))
    }

    fun getDoubleB(bb: ByteBuffer, bi: Int): Double {
        return Double.longBitsToDouble(getLongB(bb, bi))
    }

    fun getDoubleB(a: Long): Double {
        return Double.longBitsToDouble(getLongB(a))
    }

    fun getDouble(bb: ByteBuffer, bi: Int, bigEndian: Boolean): Double {
        return if (bigEndian) getDoubleB(bb, bi) else getDoubleL(bb, bi)
    }

    fun getDouble(a: Long, bigEndian: Boolean): Double {
        return if (bigEndian) getDoubleB(a) else getDoubleL(a)
    }

    fun putDoubleL(bb: ByteBuffer, bi: Int, x: Double) {
        putLongL(bb, bi, Double.doubleToRawLongBits(x))
    }

    fun putDoubleL(a: Long, x: Double) {
        putLongL(a, Double.doubleToRawLongBits(x))
    }

    fun putDoubleB(bb: ByteBuffer, bi: Int, x: Double) {
        putLongB(bb, bi, Double.doubleToRawLongBits(x))
    }

    fun putDoubleB(a: Long, x: Double) {
        putLongB(a, Double.doubleToRawLongBits(x))
    }

    fun putDouble(bb: ByteBuffer, bi: Int, x: Double, bigEndian: Boolean) {
        if (bigEndian)
            putDoubleB(bb, bi, x)
        else
            putDoubleL(bb, bi, x)
    }

    fun putDouble(a: Long, x: Double, bigEndian: Boolean) {
        if (bigEndian)
            putDoubleB(a, x)
        else
            putDoubleL(a, x)
    }

    private fun _get(a: Long): Byte {
        return unsafe.getByte(a)
    }

    private fun _put(a: Long, b: Byte) {
        unsafe.putByte(a, b)
    }

    fun unsafe(): Unsafe {
        return unsafe
    }

    fun byteOrder(): ByteOrder {
        if (byteOrder == null)
            throw Error("Unknown byte order")
        return byteOrder
    }

    fun pageSize(): Int {
        if (pageSize == -1)
            pageSize = unsafe().pageSize()
        return pageSize
    }

    fun pageCount(size: Long): Int {
        return (size + pageSize().toLong() - 1L).toInt() / pageSize()
    }

    fun unaligned(): Boolean {
        if (unalignedKnown)
            return unaligned
//        val arch = AccessController.doPrivileged(
//                sun.security.action.GetPropertyAction("os.arch"))
//        unaligned = (arch.equals("i386") || arch.equals("x86")
//                || arch.equals("amd64") || arch.equals("x86_64")
//                || arch.equals("ppc64") || arch.equals("ppc64le"))
//        unalignedKnown = true
        return unaligned
    }

    // These methods should be called whenever direct memory is allocated or
    // freed.  They allow the user to control the amount of direct memory
    // which a process may access.  All sizes are specified in bytes.
    fun reserveMemory(size: Long, cap: Int) {

        if (!memoryLimitSet && VM.isBooted()) {
            maxMemory = VM.maxDirectMemory()
            memoryLimitSet = true
        }

        // optimist!
        if (tryReserveMemory(size, cap)) {
            return
        }

//        val jlra = SharedSecrets.getJavaLangRefAccess()

        // retry while helping enqueue pending Reference objects
        // which includes executing pending Cleaner(s) which includes
        // Cleaner(s) that free direct buffer memory
//        while (jlra.tryHandlePendingReference()) {
        if (tryReserveMemory(size, cap)) {
            return
        }
//        }

        // trigger VM's Reference processing
        System.gc()

        // a retry loop with exponential back-off delays
        // (this gives VM some time to do it's job)
        var interrupted = false
        try {
            var sleepTime: Long = 1
            var sleeps = 0
            while (true) {
                if (tryReserveMemory(size, cap)) {
                    return
                }
                if (sleeps >= MAX_SLEEPS) {
                    break
                }
//                if (!jlra.tryHandlePendingReference()) {
//                    try {
//                        Thread.sleep(sleepTime)
//                        sleepTime = sleepTime shl 1
//                        sleeps++
//                    } catch (e: InterruptedException) {
//                        interrupted = true
//                    }
//
//                }
            }

            // no luck
//            throw OutOfMemoryError("Direct buffer memory")

        } finally {
            if (interrupted) {
                // don't swallow interrupts
//                Thread.currentThread().interrupt()
            }
        }
    }

    private fun tryReserveMemory(size: Long, cap: Int): Boolean {

        // -XX:MaxDirectMemorySize limits the total capacity rather than the
        // actual memory usage, which will differ when buffers are page
        // aligned.
        var totalCap: Long = totalCapacity.get()
        while (cap <= maxMemory - totalCap) {
            if (totalCapacity.compareAndSet(totalCap, totalCap + cap)) {
                reservedMemory.addAndGet(size)
                count.incrementAndGet()
                return true
            }
            totalCap = totalCapacity.get()
        }

        return false
    }


    fun unreserveMemory(size: Long, cap: Int) {
        val cnt = count.decrementAndGet()
        val reservedMem = reservedMemory.addAndGet(-size)
        val totalCap = totalCapacity.addAndGet(-cap.toLong())
        assert(cnt >= 0 && reservedMem >= 0 && totalCap >= 0)
    }


    // -- Monitoring of direct buffer usage --

    init {
        // setup access to this package in SharedSecrets
//        sun.misc.SharedSecrets.setJavaNioAccess(
//                object : sun.misc.JavaNioAccess() {
//                    val directBufferPool: sun.misc.JavaNioAccess.BufferPool
//                        @Override
//                        get() = object : sun.misc.JavaNioAccess.BufferPool() {
//                            val name: String
//                                @Override
//                                get() = "direct"
//                            val count: Long
//                                @Override
//                                get() = Bits.count.get()
//                            val totalCapacity: Long
//                                @Override
//                                get() = Bits.totalCapacity.get()
//                            val memoryUsed: Long
//                                @Override
//                                get() = Bits.reservedMemory.get()
//                        }
//
//                    @Override
//                    fun newDirectByteBuffer(addr: Long, cap: Int, ob: Object): ByteBuffer {
//                        return DirectByteBuffer(addr, cap, ob)
//                    }
//
//                    @Override
//                    fun truncate(buf: Buffer) {
//                        buf.truncate()
//                    }
//                })
    }

    // These methods do no bounds checking.  Verification that the copy will not
    // result in memory corruption should be done prior to invocation.
    // All positions and lengths are specified in bytes.

    /**
     * Copy from given source array to destination address.
     *
     * @param   src
     * source array
     * @param   srcBaseOffset
     * offset of first element of storage in source array
     * @param   srcPos
     * offset within source array of the first element to read
     * @param   dstAddr
     * destination address
     * @param   length
     * number of bytes to copy
     */
    fun copyFromArray(src: Any, srcBaseOffset: Long, srcPos: Long,
                      dstAddr: Long, length: Long) {
        var dstAddr = dstAddr
        var length = length
        var offset = srcBaseOffset + srcPos
        while (length > 0) {
            val size = if (length > UNSAFE_COPY_THRESHOLD) UNSAFE_COPY_THRESHOLD else length
            unsafe.copyMemory(src, offset, null, dstAddr, size)
            length -= size
            offset += size
            dstAddr += size
        }
    }

    /**
     * Copy from source address into given destination array.
     *
     * @param   srcAddr
     * source address
     * @param   dst
     * destination array
     * @param   dstBaseOffset
     * offset of first element of storage in destination array
     * @param   dstPos
     * offset within destination array of the first element to write
     * @param   length
     * number of bytes to copy
     */
    fun copyToArray(srcAddr: Long, dst: Any, dstBaseOffset: Long, dstPos: Long,
                    length: Long) {
        var srcAddr = srcAddr
        var length = length
        var offset = dstBaseOffset + dstPos
        while (length > 0) {
            val size = if (length > UNSAFE_COPY_THRESHOLD) UNSAFE_COPY_THRESHOLD else length
            unsafe.copyMemory(null, srcAddr, dst, offset, size)
            length -= size
            srcAddr += size
            offset += size
        }
    }

    fun copyFromCharArray(src: Any, srcPos: Long, dstAddr: Long, length: Long) {
        copyFromShortArray(src, srcPos, dstAddr, length)
    }

    fun copyToCharArray(srcAddr: Long, dst: Any, dstPos: Long, length: Long) {
        copyToShortArray(srcAddr, dst, dstPos, length)
    }

    fun copyFromShortArray(src: Any, srcPos: Long, dstAddr: Long, length: Long) {
        TODO("not implemented")
    }

    fun copyToShortArray(srcAddr: Long, dst: Any, dstPos: Long, length: Long) {
        TODO("not implemented")
    }

    fun copyFromIntArray(src: Any, srcPos: Long, dstAddr: Long, length: Long) {
        TODO("not implemented")
    }

    fun copyToIntArray(srcAddr: Long, dst: Any, dstPos: Long, length: Long) {
        TODO("not implemented")
    }

    fun copyFromLongArray(src: Any, srcPos: Long, dstAddr: Long, length: Long) {
        TODO("not implemented")
    }

    fun copyToLongArray(srcAddr: Long, dst: Any, dstPos: Long, length: Long) {
        TODO("not implemented")
    }

}










