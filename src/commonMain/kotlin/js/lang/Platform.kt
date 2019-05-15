package js.lang

import js.nio.ByteOrder

expect object Platform {
    fun byteorder(): ByteOrder
}