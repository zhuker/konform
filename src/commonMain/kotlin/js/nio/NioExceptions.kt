package js.nio

/**
 * Unchecked exception thrown when a content-mutation method such as
 * <tt>put</tt> or <tt>compact</tt> is invoked upon a read-only buffer.
 *
 * @since 1.4
 */
class ReadOnlyBufferException : UnsupportedOperationException() {
    companion object {
        private val serialVersionUID = -1210063976496234090L
    }
}


/**
 * Unchecked exception thrown when an attempt is made to reset a buffer
 * when its mark is not defined.
 *
 * @since 1.4
 */
class InvalidMarkException : IllegalStateException() {
    companion object {
        private val serialVersionUID = 1698329710438510774L
    }
}

/**
 * Unchecked exception thrown when a relative *get* operation reaches
 * the source buffer's limit.
 *
 * @since 1.4
 */
class BufferUnderflowException : RuntimeException() {
    companion object {
        private val serialVersionUID = -1713313658691622206L
    }
}


/**
 * Unchecked exception thrown when a relative *put* operation reaches
 * the target buffer's limit.
 *
 * @since 1.4
 */
class BufferOverflowException : RuntimeException() {
    companion object {
        private val serialVersionUID = -5484897634319144535L
    }
}
