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

/**
 * Exception thrown when attempting to retrieve the result of a task
 * that aborted by throwing an exception. This exception can be
 * inspected using the [.getCause] method.
 *
 * @see Future
 *
 * @since 1.5
 * @author Doug Lea
 */
class ExecutionException : Exception {

    /**
     * Constructs an `ExecutionException` with no detail message.
     * The cause is not initialized, and may subsequently be
     * initialized by a call to [initCause][.initCause].
     */
    protected constructor() {}

    /**
     * Constructs an `ExecutionException` with the specified detail
     * message. The cause is not initialized, and may subsequently be
     * initialized by a call to [initCause][.initCause].
     *
     * @param message the detail message
     */
    protected constructor(message: String) : super(message) {}

    /**
     * Constructs an `ExecutionException` with the specified detail
     * message and cause.
     *
     * @param  message the detail message
     * @param  cause the cause (which is saved for later retrieval by the
     * [.getCause] method)
     */
    constructor(message: String, cause: Throwable) : super(message, cause) {}

    /**
     * Constructs an `ExecutionException` with the specified cause.
     * The detail message is set to `(cause == null ? null :
     * cause.toString())` (which typically contains the class and
     * detail message of `cause`).
     *
     * @param  cause the cause (which is saved for later retrieval by the
     * [.getCause] method)
     */
    constructor(cause: Throwable) : super(cause) {}

    companion object {
        private val serialVersionUID = 7830266012832686185L
    }
}
