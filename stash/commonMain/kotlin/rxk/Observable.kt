package rxk

interface IObserver<T> {
    fun onNext(value: T)
    fun onError(exception: Any)
    fun onCompleted()
}

interface ObservableStatic {
    fun range(start: Int, count: Int): Observable<Int>
    fun <T> fromArray(sources: Array<T>): Observable<T>
    fun <T> create(create: (observer: IObserver<T>) -> Unit): Observable<T>
}

expect class ObservableStaticImpl : ObservableStatic

expect class Observable<T> {
    fun <U> map(mapper: (x: T) -> U): Observable<U>
    fun <U> concatMap(mapper: (x: T) -> Observable<U>): Observable<U>
    fun subscribe(onNext: (next: T) -> Unit, onError: (err: Any) -> Unit)

    companion object : ObservableStatic

    fun take(i: Int): Observable<T>
    fun toArray(): Observable<Array<T>>
}