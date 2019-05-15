package js.lang

fun String.charAt(i: Int): Char = this[i]
//fun String.length(): Int = this.length

fun Double.Companion.doubleToRawLongBits(x: Double): Long = x.toRawBits()

fun Short.Companion.reverseBytes(x: Short): Short {
    TODO("not implemented")
}

fun Char.Companion.reverseBytes(x: Char): Char {
    TODO("not implemented")
}

fun Int.Companion.reverseBytes(x: Int): Int {
    TODO("not implemented")
}

fun Long.Companion.reverseBytes(x: Long): Long {
    TODO("not implemented")
}

inline infix fun Byte.shl(i: Int): Int = this.toInt() shl i

inline infix fun Byte.and(i: Int): Int = this.toInt() and i

inline infix fun Short.shr(i: Int): Int = this.toInt() shr i

fun Float.Companion.intBitsToFloat(intL: Int): Float = Float.fromBits(intL)

fun Float.Companion.floatToRawIntBits(x: Float): Int = x.toRawBits()

fun Double.Companion.longBitsToDouble(longL: Long): Double {
    TODO("not implemented")
}

fun assert(b: Boolean) {
    if (!b) throw IllegalStateException("assertion failed")
}

object VM {
    fun maxDirectMemory(): Long = Int.MAX_VALUE.toLong()

    fun isBooted(): Boolean = true
}

object System {
    fun gc() {
    }

    fun arraycopy(src: Any?, srcPos: Int,
                  dest: Any?, destPos: Int,
                  length: Int) {
        if (src is ByteArray && dest is ByteArray) {
            src.copyInto(dest, destinationOffset = destPos, startIndex = srcPos, endIndex = srcPos + length)
            return
        } else if (src is IntArray && dest is IntArray) {
            src.copyInto(dest, destinationOffset = destPos, startIndex = srcPos, endIndex = srcPos + length)
            return
        }
        TODO("not implemented")
    }
}

object Unsafe {
    fun getUnsafe(): Unsafe = this

    fun getByte(ptr: Long): Byte {
        TODO("not implemented")
    }

    fun putByte(ptr: Long, b: Byte) {
        TODO("not implemented")
    }

    fun allocateMemory(sz: Int): Long {
        TODO("not implemented")
    }

    fun pageSize(): Int {
        return 4096
    }

    fun copyMemory(src: Any?, offset: Long, dst: Any?, dstAddr: Long, size: Long) {
        TODO("not implemented")
    }

    fun putLong(ptr: Long, value: Long) {
        TODO("not implemented")
    }

    fun freeMemory(ptr: Long) {
        TODO("not implemented")
    }
}

object Runtime {
    fun getRuntime(): Runtime {
        return this
    }

    fun availableProcessors(): Int {
        return 1
    }
}

/**
 * Thrown when a thread is waiting, sleeping, or otherwise occupied,
 * and the thread is interrupted, either before or during the activity.
 * Occasionally a method may wish to test whether the current
 * thread has been interrupted, and if so, to immediately throw
 * this exception.  The following code can be used to achieve
 * this effect:
 * <pre>
 * if (Thread.interrupted())  // Clears interrupted status!
 * throw new InterruptedException();
</pre> *
 *
 * @author  Frank Yellin
 * @see java.lang.Object.wait
 * @see java.lang.Object.wait
 * @see java.lang.Object.wait
 * @see java.lang.Thread.sleep
 * @see java.lang.Thread.interrupt
 * @see java.lang.Thread.interrupted
 * @since   JDK1.0
 */
open class InterruptedException : Exception {

    /**
     * Constructs an `InterruptedException` with no detail  message.
     */
    constructor() : super()

    /**
     * Constructs an `InterruptedException` with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    constructor(s: String) : super(s)

}

fun Exception.printStackTrace() {
    println(this.message)
}

open class Thread {
    fun join(ms: Long, ns: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun sleep(msec: Long) {

        }

        fun sleep(msec: Long, ns: Int) {

        }
    }
}


/**
 * Thrown when an application tries to call an abstract method.
 * Normally, this error is caught by the compiler; this error can
 * only occur at run time if the definition of some class has
 * incompatibly changed since the currently executing method was last
 * compiled.
 *
 * @author  unascribed
 * @since   JDK1.0
 */
class AbstractMethodError : Exception {

    /**
     * Constructs an `AbstractMethodError` with no detail  message.
     */
    constructor() : super() {}

    /**
     * Constructs an `AbstractMethodError` with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    constructor(s: String) : super(s) {}

    companion object {
        private val serialVersionUID = -1654391082989018462L
    }
}


/**
 * The `Runnable` interface should be implemented by any
 * class whose instances are intended to be executed by a thread. The
 * class must define a method of no arguments called `run`.
 *
 *
 * This interface is designed to provide a common protocol for objects that
 * wish to execute code while they are active. For example,
 * `Runnable` is implemented by class `Thread`.
 * Being active simply means that a thread has been started and has not
 * yet been stopped.
 *
 *
 * In addition, `Runnable` provides the means for a class to be
 * active while not subclassing `Thread`. A class that implements
 * `Runnable` can run without subclassing `Thread`
 * by instantiating a `Thread` instance and passing itself in
 * as the target.  In most cases, the `Runnable` interface should
 * be used if you are only planning to override the `run()`
 * method and no other `Thread` methods.
 * This is important because classes should not be subclassed
 * unless the programmer intends on modifying or enhancing the fundamental
 * behavior of the class.
 *
 * @author  Arthur van Hoff
 * @see java.lang.Thread
 *
 * @see java.util.concurrent.Callable
 *
 * @since   JDK1.0
 */
//@FunctionalInterface
interface Runnable {
    /**
     * When an object implementing interface `Runnable` is used
     * to create a thread, starting the thread causes the object's
     * `run` method to be called in that separately executing
     * thread.
     *
     *
     * The general contract of the method `run` is that it may
     * take any action whatsoever.
     *
     * @see java.lang.Thread.run
     */
    fun run()
}
