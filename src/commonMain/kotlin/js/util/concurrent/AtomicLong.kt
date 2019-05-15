package js.util.concurrent

class AtomicLong(private var value: Long = 0L) {
    fun compareAndSet(expect: Long, update: Long): Boolean {
        if (expect == value) {
            value = update
            return true
        }
        return false
    }

    fun get(): Long {
        return value
    }

    fun addAndGet(delta: Long): Long {
        value += delta
        return value
    }

    fun incrementAndGet(): Long {
        value++
        return value
    }

    fun decrementAndGet(): Long {
        value--
        return value
    }

}