package org.jcodec.common

object JCodecUtil2 {
    fun asciiString(s: String): ByteArray = s.map { it.toByte() }.toByteArray()
}