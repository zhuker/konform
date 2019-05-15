/*
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

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package js.util.concurrent

import js.lang.AbstractMethodError
import js.lang.Thread
import js.lang.Thread.Companion.sleep

/**
 * A `TimeUnit` represents time durations at a given unit of
 * granularity and provides utility methods to convert across units,
 * and to perform timing and delay operations in these units.  A
 * `TimeUnit` does not maintain time information, but only
 * helps organize and use time representations that may be maintained
 * separately across various contexts.  A nanosecond is defined as one
 * thousandth of a microsecond, a microsecond as one thousandth of a
 * millisecond, a millisecond as one thousandth of a second, a minute
 * as sixty seconds, an hour as sixty minutes, and a day as twenty four
 * hours.
 *
 *
 * A `TimeUnit` is mainly used to inform time-based methods
 * how a given timing parameter should be interpreted. For example,
 * the following code will timeout in 50 milliseconds if the [ ] is not available:
 *
 * <pre> `Lock lock = ...;
 * if (lock.tryLock(50L, TimeUnit.MILLISECONDS)) ...`</pre>
 *
 * while this code will timeout in 50 seconds:
 * <pre> `Lock lock = ...;
 * if (lock.tryLock(50L, TimeUnit.SECONDS)) ...`</pre>
 *
 * Note however, that there is no guarantee that a particular timeout
 * implementation will be able to notice the passage of time at the
 * same granularity as the given `TimeUnit`.
 *
 * @since 1.5
 * @author Doug Lea
 */
enum class TimeUnit {
    /**
     * Time unit representing one thousandth of a microsecond
     */
    NANOSECONDS {
        override fun toNanos(d: Long): Long {
            return d
        }

        override fun toMicros(d: Long): Long {
            return d / (C1 / C0)
        }

        override fun toMillis(d: Long): Long {
            return d / (C2 / C0)
        }

        override fun toSeconds(d: Long): Long {
            return d / (C3 / C0)
        }

        override fun toMinutes(d: Long): Long {
            return d / (C4 / C0)
        }

        override fun toHours(d: Long): Long {
            return d / (C5 / C0)
        }

        override fun toDays(d: Long): Long {
            return d / (C6 / C0)
        }

        override fun convert(d: Long, u: TimeUnit): Long {
            return u.toNanos(d)
        }

        override fun excessNanos(d: Long, m: Long): Int {
            return (d - m * C2).toInt()
        }
    },

    /**
     * Time unit representing one thousandth of a millisecond
     */
    MICROSECONDS {
        override fun toNanos(d: Long): Long {
            return x(d, C1 / C0, MAX / (C1 / C0))
        }

        override fun toMicros(d: Long): Long {
            return d
        }

        override fun toMillis(d: Long): Long {
            return d / (C2 / C1)
        }

        override fun toSeconds(d: Long): Long {
            return d / (C3 / C1)
        }

        override fun toMinutes(d: Long): Long {
            return d / (C4 / C1)
        }

        override fun toHours(d: Long): Long {
            return d / (C5 / C1)
        }

        override fun toDays(d: Long): Long {
            return d / (C6 / C1)
        }

        override fun convert(d: Long, u: TimeUnit): Long {
            return u.toMicros(d)
        }

        override fun excessNanos(d: Long, m: Long): Int {
            return (d * C1 - m * C2).toInt()
        }
    },

    /**
     * Time unit representing one thousandth of a second
     */
    MILLISECONDS {
        override fun toNanos(d: Long): Long {
            return x(d, C2 / C0, MAX / (C2 / C0))
        }

        override fun toMicros(d: Long): Long {
            return x(d, C2 / C1, MAX / (C2 / C1))
        }

        override fun toMillis(d: Long): Long {
            return d
        }

        override fun toSeconds(d: Long): Long {
            return d / (C3 / C2)
        }

        override fun toMinutes(d: Long): Long {
            return d / (C4 / C2)
        }

        override fun toHours(d: Long): Long {
            return d / (C5 / C2)
        }

        override fun toDays(d: Long): Long {
            return d / (C6 / C2)
        }

        override fun convert(d: Long, u: TimeUnit): Long {
            return u.toMillis(d)
        }

        override fun excessNanos(d: Long, m: Long): Int {
            return 0
        }
    },

    /**
     * Time unit representing one second
     */
    SECONDS {
        override fun toNanos(d: Long): Long {
            return x(d, C3 / C0, MAX / (C3 / C0))
        }

        override fun toMicros(d: Long): Long {
            return x(d, C3 / C1, MAX / (C3 / C1))
        }

        override fun toMillis(d: Long): Long {
            return x(d, C3 / C2, MAX / (C3 / C2))
        }

        override fun toSeconds(d: Long): Long {
            return d
        }

        override fun toMinutes(d: Long): Long {
            return d / (C4 / C3)
        }

        override fun toHours(d: Long): Long {
            return d / (C5 / C3)
        }

        override fun toDays(d: Long): Long {
            return d / (C6 / C3)
        }

        override fun convert(d: Long, u: TimeUnit): Long {
            return u.toSeconds(d)
        }

        override fun excessNanos(d: Long, m: Long): Int {
            return 0
        }
    },

    /**
     * Time unit representing sixty seconds
     */
    MINUTES {
        override fun toNanos(d: Long): Long {
            return x(d, C4 / C0, MAX / (C4 / C0))
        }

        override fun toMicros(d: Long): Long {
            return x(d, C4 / C1, MAX / (C4 / C1))
        }

        override fun toMillis(d: Long): Long {
            return x(d, C4 / C2, MAX / (C4 / C2))
        }

        override fun toSeconds(d: Long): Long {
            return x(d, C4 / C3, MAX / (C4 / C3))
        }

        override fun toMinutes(d: Long): Long {
            return d
        }

        override fun toHours(d: Long): Long {
            return d / (C5 / C4)
        }

        override fun toDays(d: Long): Long {
            return d / (C6 / C4)
        }

        override fun convert(d: Long, u: TimeUnit): Long {
            return u.toMinutes(d)
        }

        override fun excessNanos(d: Long, m: Long): Int {
            return 0
        }
    },

    /**
     * Time unit representing sixty minutes
     */
    HOURS {
        override fun toNanos(d: Long): Long {
            return x(d, C5 / C0, MAX / (C5 / C0))
        }

        override fun toMicros(d: Long): Long {
            return x(d, C5 / C1, MAX / (C5 / C1))
        }

        override fun toMillis(d: Long): Long {
            return x(d, C5 / C2, MAX / (C5 / C2))
        }

        override fun toSeconds(d: Long): Long {
            return x(d, C5 / C3, MAX / (C5 / C3))
        }

        override fun toMinutes(d: Long): Long {
            return x(d, C5 / C4, MAX / (C5 / C4))
        }

        override fun toHours(d: Long): Long {
            return d
        }

        override fun toDays(d: Long): Long {
            return d / (C6 / C5)
        }

        override fun convert(d: Long, u: TimeUnit): Long {
            return u.toHours(d)
        }

        override fun excessNanos(d: Long, m: Long): Int {
            return 0
        }
    },

    /**
     * Time unit representing twenty four hours
     */
    DAYS {
        override fun toNanos(d: Long): Long {
            return x(d, C6 / C0, MAX / (C6 / C0))
        }

        override fun toMicros(d: Long): Long {
            return x(d, C6 / C1, MAX / (C6 / C1))
        }

        override fun toMillis(d: Long): Long {
            return x(d, C6 / C2, MAX / (C6 / C2))
        }

        override fun toSeconds(d: Long): Long {
            return x(d, C6 / C3, MAX / (C6 / C3))
        }

        override fun toMinutes(d: Long): Long {
            return x(d, C6 / C4, MAX / (C6 / C4))
        }

        override fun toHours(d: Long): Long {
            return x(d, C6 / C5, MAX / (C6 / C5))
        }

        override fun toDays(d: Long): Long {
            return d
        }

        override fun convert(d: Long, u: TimeUnit): Long {
            return u.toDays(d)
        }

        override fun excessNanos(d: Long, m: Long): Int {
            return 0
        }
    };

    // To maintain full signature compatibility with 1.5, and to improve the
    // clarity of the generated javadoc (see 6287639: Abstract methods in
    // enum classes should not be listed as abstract), method convert
    // etc. are not declared abstract but otherwise act as abstract methods.

    /**
     * Converts the given time duration in the given unit to this unit.
     * Conversions from finer to coarser granularities truncate, so
     * lose precision. For example, converting `999` milliseconds
     * to seconds results in `0`. Conversions from coarser to
     * finer granularities with arguments that would numerically
     * overflow saturate to `Long.MIN_VALUE` if negative or
     * `Long.MAX_VALUE` if positive.
     *
     *
     * For example, to convert 10 minutes to milliseconds, use:
     * `TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES)`
     *
     * @param sourceDuration the time duration in the given `sourceUnit`
     * @param sourceUnit the unit of the `sourceDuration` argument
     * @return the converted duration in this unit,
     * or `Long.MIN_VALUE` if conversion would negatively
     * overflow, or `Long.MAX_VALUE` if it would positively overflow.
     */
    open fun convert(sourceDuration: Long, sourceUnit: TimeUnit): Long {
        throw AbstractMethodError()
    }

    /**
     * Equivalent to
     * [NANOSECONDS.convert(duration, this)][.convert].
     * @param duration the duration
     * @return the converted duration,
     * or `Long.MIN_VALUE` if conversion would negatively
     * overflow, or `Long.MAX_VALUE` if it would positively overflow.
     */
    open fun toNanos(duration: Long): Long {
        throw AbstractMethodError()
    }

    /**
     * Equivalent to
     * [MICROSECONDS.convert(duration, this)][.convert].
     * @param duration the duration
     * @return the converted duration,
     * or `Long.MIN_VALUE` if conversion would negatively
     * overflow, or `Long.MAX_VALUE` if it would positively overflow.
     */
    open fun toMicros(duration: Long): Long {
        throw AbstractMethodError()
    }

    /**
     * Equivalent to
     * [MILLISECONDS.convert(duration, this)][.convert].
     * @param duration the duration
     * @return the converted duration,
     * or `Long.MIN_VALUE` if conversion would negatively
     * overflow, or `Long.MAX_VALUE` if it would positively overflow.
     */
    open fun toMillis(duration: Long): Long {
        throw AbstractMethodError()
    }

    /**
     * Equivalent to
     * [SECONDS.convert(duration, this)][.convert].
     * @param duration the duration
     * @return the converted duration,
     * or `Long.MIN_VALUE` if conversion would negatively
     * overflow, or `Long.MAX_VALUE` if it would positively overflow.
     */
    open fun toSeconds(duration: Long): Long {
        throw AbstractMethodError()
    }

    /**
     * Equivalent to
     * [MINUTES.convert(duration, this)][.convert].
     * @param duration the duration
     * @return the converted duration,
     * or `Long.MIN_VALUE` if conversion would negatively
     * overflow, or `Long.MAX_VALUE` if it would positively overflow.
     * @since 1.6
     */
    open fun toMinutes(duration: Long): Long {
        throw AbstractMethodError()
    }

    /**
     * Equivalent to
     * [HOURS.convert(duration, this)][.convert].
     * @param duration the duration
     * @return the converted duration,
     * or `Long.MIN_VALUE` if conversion would negatively
     * overflow, or `Long.MAX_VALUE` if it would positively overflow.
     * @since 1.6
     */
    open fun toHours(duration: Long): Long {
        throw AbstractMethodError()
    }

    /**
     * Equivalent to
     * [DAYS.convert(duration, this)][.convert].
     * @param duration the duration
     * @return the converted duration
     * @since 1.6
     */
    open fun toDays(duration: Long): Long {
        throw AbstractMethodError()
    }

    /**
     * Utility to compute the excess-nanosecond argument to wait,
     * sleep, join.
     * @param d the duration
     * @param m the number of milliseconds
     * @return the number of nanoseconds
     */
    internal abstract fun excessNanos(d: Long, m: Long): Int

    /**
     * Performs a timed [Object.wait]
     * using this time unit.
     * This is a convenience method that converts timeout arguments
     * into the form required by the `Object.wait` method.
     *
     *
     * For example, you could implement a blocking `poll`
     * method (see [BlockingQueue.poll])
     * using:
     *
     * <pre> `public synchronized Object poll(long timeout, TimeUnit unit)
     * throws InterruptedException {
     * while (empty) {
     * unit.timedWait(this, timeout);
     * ...
     * }
     * }`</pre>
     *
     * @param obj the object to wait on
     * @param timeout the maximum time to wait. If less than
     * or equal to zero, do not wait at all.
     * @throws InterruptedException if interrupted while waiting
     */
//    @Throws(InterruptedException::class)
    fun timedWait(obj: Any, timeout: Long) {
        if (timeout > 0) {
            val ms = toMillis(timeout)
            val ns = excessNanos(timeout, ms)
            obj.wait(ms, ns)
        }
    }

    /**
     * Performs a timed [Thread.join]
     * using this time unit.
     * This is a convenience method that converts time arguments into the
     * form required by the `Thread.join` method.
     *
     * @param thread the thread to wait for
     * @param timeout the maximum time to wait. If less than
     * or equal to zero, do not wait at all.
     * @throws InterruptedException if interrupted while waiting
     */
//    @Throws(InterruptedException::class)
    fun timedJoin(thread: Thread, timeout: Long) {
        if (timeout > 0) {
            val ms = toMillis(timeout)
            val ns = excessNanos(timeout, ms)
            thread.join(ms, ns)
        }
    }

    /**
     * Performs a [Thread.sleep] using
     * this time unit.
     * This is a convenience method that converts time arguments into the
     * form required by the `Thread.sleep` method.
     *
     * @param timeout the minimum time to sleep. If less than
     * or equal to zero, do not sleep at all.
     * @throws InterruptedException if interrupted while sleeping
     */
//    @Throws(InterruptedException::class)
    fun sleep(timeout: Long) {
        if (timeout > 0) {
            val ms = toMillis(timeout)
            val ns = excessNanos(timeout, ms)
            Thread.sleep(ms, ns)
        }
    }

    companion object {

        // Handy constants for conversion methods
        internal val C0 = 1L
        internal val C1 = C0 * 1000L
        internal val C2 = C1 * 1000L
        internal val C3 = C2 * 1000L
        internal val C4 = C3 * 60L
        internal val C5 = C4 * 60L
        internal val C6 = C5 * 24L

        internal val MAX = Long.MAX_VALUE

        /**
         * Scale d by m, checking for overflow.
         * This has a short name to make above code more readable.
         */
        internal fun x(d: Long, m: Long, over: Long): Long {
            if (d > over) return Long.MAX_VALUE
            return if (d < -over) Long.MIN_VALUE else d * m
        }
    }

}

private fun Any.wait(ms: Long, ns: Int) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
