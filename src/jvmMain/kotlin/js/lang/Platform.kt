package js.lang

import js.nio.ByteOrder

actual object Platform {
    actual fun byteorder(): ByteOrder {
        val nativeOrder = java.nio.ByteOrder.nativeOrder()
        return when (nativeOrder) {
            java.nio.ByteOrder.BIG_ENDIAN -> js.nio.ByteOrder.BIG_ENDIAN
            java.nio.ByteOrder.LITTLE_ENDIAN -> js.nio.ByteOrder.LITTLE_ENDIAN
            else -> throw IllegalStateException("unknown byteorder $nativeOrder")
        }
    }
}