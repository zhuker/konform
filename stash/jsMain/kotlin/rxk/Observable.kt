package rxk

import kotlin.js.Promise

actual class Observable<T>(val o: Rx.Observable<T>) {
    actual fun <U> map(mapper: (x: T) -> U): Observable<U> {
        o.map(mapper)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual companion object : ObservableStatic by ObservableStaticImpl() {
        fun <T> fromPromise(arrayBuffer: Promise<T>): Observable<T> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    actual fun <U> concatMap(mapper: (x: T) -> Observable<U>): Observable<U> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual fun take(i: Int): Observable<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual fun toArray(): Observable<Array<T>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun shareReplay(): Observable<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual fun subscribe(onNext: (next: T) -> Unit, onError: (err: Any) -> Unit) {
    }

}

actual class ObservableStaticImpl : ObservableStatic {
    override fun <T> create(create: (observer: IObserver<T>) -> Unit): Observable<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> fromArray(sources: Array<T>): Observable<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun range(start: Int, count: Int): Observable<Int> {
        Rx.Observable.range(start, count)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}