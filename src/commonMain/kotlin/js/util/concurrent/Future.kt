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
 * A `Future` represents the result of an asynchronous
 * computation.  Methods are provided to check if the computation is
 * complete, to wait for its completion, and to retrieve the result of
 * the computation.  The result can only be retrieved using method
 * `get` when the computation has completed, blocking if
 * necessary until it is ready.  Cancellation is performed by the
 * `cancel` method.  Additional methods are provided to
 * determine if the task completed normally or was cancelled. Once a
 * computation has completed, the computation cannot be cancelled.
 * If you would like to use a `Future` for the sake
 * of cancellability but not provide a usable result, you can
 * declare types of the form `Future<?>` and
 * return `null` as a result of the underlying task.
 *
 *
 *
 * **Sample Usage** (Note that the following classes are all
 * made-up.)
 * <pre> `interface ArchiveSearcher { String search(String target); }
 * class App {
 * ExecutorService executor = ...
 * ArchiveSearcher searcher = ...
 * void showSearch(final String target)
 * throws InterruptedException {
 * Future<String> future
 * = executor.submit(new Callable<String>() {
 * public String call() {
 * return searcher.search(target);
 * }});
 * displayOtherThings(); // do other things while searching
 * try {
 * displayText(future.get()); // use future
 * } catch (ExecutionException ex) { cleanup(); return; }
 * }
 * }`</pre>
 *
 * The [FutureTask] class is an implementation of `Future` that
 * implements `Runnable`, and so may be executed by an `Executor`.
 * For example, the above construction with `submit` could be replaced by:
 * <pre> `FutureTask<String> future =
 * new FutureTask<String>(new Callable<String>() {
 * public String call() {
 * return searcher.search(target);
 * }});
 * executor.execute(future);`</pre>
 *
 *
 * Memory consistency effects: Actions taken by the asynchronous computation
 * [ *happen-before*](package-summary.html#MemoryVisibility)
 * actions following the corresponding `Future.get()` in another thread.
 *
 * @see FutureTask
 *
 * @see Executor
 *
 * @since 1.5
 * @author Doug Lea
 * @param <V> The result type returned by this Future's `get` method
</V> */
interface Future<V> {

    /**
     * Returns `true` if this task was cancelled before it completed
     * normally.
     *
     * @return `true` if this task was cancelled before it completed
     */
    val isCancelled: Boolean

    /**
     * Returns `true` if this task completed.
     *
     * Completion may be due to normal termination, an exception, or
     * cancellation -- in all of these cases, this method will return
     * `true`.
     *
     * @return `true` if this task completed
     */
    val isDone: Boolean

    /**
     * Attempts to cancel execution of this task.  This attempt will
     * fail if the task has already completed, has already been cancelled,
     * or could not be cancelled for some other reason. If successful,
     * and this task has not started when `cancel` is called,
     * this task should never run.  If the task has already started,
     * then the `mayInterruptIfRunning` parameter determines
     * whether the thread executing this task should be interrupted in
     * an attempt to stop the task.
     *
     *
     * After this method returns, subsequent calls to [.isDone] will
     * always return `true`.  Subsequent calls to [.isCancelled]
     * will always return `true` if this method returned `true`.
     *
     * @param mayInterruptIfRunning `true` if the thread executing this
     * task should be interrupted; otherwise, in-progress tasks are allowed
     * to complete
     * @return `false` if the task could not be cancelled,
     * typically because it has already completed normally;
     * `true` otherwise
     */
    fun cancel(mayInterruptIfRunning: Boolean): Boolean

    /**
     * Waits if necessary for the computation to complete, and then
     * retrieves its result.
     *
     * @return the computed result
     * @throws CancellationException if the computation was cancelled
     * @throws ExecutionException if the computation threw an
     * exception
     * @throws InterruptedException if the current thread was interrupted
     * while waiting
     */
//    @Throws(InterruptedException::class, ExecutionException::class)
    fun get(): V

    /**
     * Waits if necessary for at most the given time for the computation
     * to complete, and then retrieves its result, if available.
     *
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @return the computed result
     * @throws CancellationException if the computation was cancelled
     * @throws ExecutionException if the computation threw an
     * exception
     * @throws InterruptedException if the current thread was interrupted
     * while waiting
     * @throws TimeoutException if the wait timed out
     */
//    @Throws(InterruptedException::class, ExecutionException::class, TimeoutException::class)
    operator fun get(timeout: Long, unit: TimeUnit): V
}
