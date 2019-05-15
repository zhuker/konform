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

package js.nio


/**
 * A typesafe enumeration for byte orders.
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 */

class ByteOrder private constructor(private val name: String) {

    /**
     * Constructs a string describing this object.
     *
     *
     *  This method returns the string <tt>"BIG_ENDIAN"</tt> for [ ][.BIG_ENDIAN] and <tt>"LITTLE_ENDIAN"</tt> for [.LITTLE_ENDIAN].
     *
     *
     * @return  The specified string
     */
    override fun toString(): String {
        return name
    }

    companion object {

        /**
         * Constant denoting big-endian byte order.  In this order, the bytes of a
         * multibyte value are ordered from most significant to least significant.
         */
        val BIG_ENDIAN = ByteOrder("BIG_ENDIAN")

        /**
         * Constant denoting little-endian byte order.  In this order, the bytes of
         * a multibyte value are ordered from least significant to most
         * significant.
         */
        val LITTLE_ENDIAN = ByteOrder("LITTLE_ENDIAN")

        /**
         * Retrieves the native byte order of the underlying platform.
         *
         *
         *  This method is defined so that performance-sensitive Java code can
         * allocate direct buffers with the same byte order as the hardware.
         * Native code libraries are often more efficient when such buffers are
         * used.
         *
         * @return  The native byte order of the hardware upon which this Java
         * virtual machine is running
         */
        fun nativeOrder(): ByteOrder {
            return Bits.byteOrder()
        }
    }

}
