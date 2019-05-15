@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
@file:JsModule("rx")

package Rx

import org.w3c.dom.events.EventTarget
import kotlin.js.*

external interface IObservable<T> {
    fun subscribe(observer: IObserver<T>): IDisposable
    fun subscribe(onNext: ((value: T) -> Unit)? = definedExternally /* null */, onError: ((exception: Any) -> Unit)? = definedExternally /* null */, onCompleted: (() -> Unit)? = definedExternally /* null */): IDisposable
    fun subscribe(onNext: ((value: T) -> Unit)? = definedExternally /* null */): IDisposable
}

open external class Observable<T> : IObservable<T> {
    override fun subscribe(observer: IObserver<T>): IDisposable = definedExternally
    override fun subscribe(onNext: ((value: T) -> Unit)?, onError: ((exception: Any) -> Unit)?, onCompleted: (() -> Unit)?): IDisposable = definedExternally
    override fun subscribe(onNext: ((value: T) -> Unit)?): IDisposable = definedExternally

    fun subscribeOnNext(onNext: (value: T) -> Unit, thisArg: Any? = definedExternally /* null */): IDisposable
    fun subscribeOnError(onError: (exception: Any) -> Unit, thisArg: Any? = definedExternally /* null */): IDisposable
    fun subscribeOnCompleted(onCompleted: () -> Unit, thisArg: Any? = definedExternally /* null */): IDisposable
    fun forEach(observer: IObserver<T>): IDisposable
    fun forEach(onNext: ((value: T) -> Unit)? = definedExternally /* null */, onError: ((exception: Any) -> Unit)? = definedExternally /* null */, onCompleted: (() -> Unit)? = definedExternally /* null */): IDisposable

    companion object : ObservableStatic by definedExternally {
    }

    fun observeOn(scheduler: IScheduler): Observable<T>
    fun subscribeOn(scheduler: IScheduler): Observable<T>
    fun toPromise(promiseCtor: Any? = definedExternally /* null */): Promise<T>
    fun <TPromise : Promise<T>> toPromise(promiseCtor: Any): TPromise
    fun toArray(): Observable<Array<T>>
    fun amb(observable: IObservable<T>): Observable<T>
    fun amb(observable: Observable<T>): Observable<T>
    fun amb(observable: Promise<T>): Observable<T>
    fun catch(handler: (exception: Any) -> dynamic /* IObservable<T> | Observable<T> | Promise<T> */): Observable<T>
    fun catch(second: IObservable<T>): Observable<T>
    fun catch(second: Observable<T>): Observable<T>
    fun catch(second: Promise<T>): Observable<T>
    fun <T2, TResult> combineLatest(second: IObservable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T2, TResult> combineLatest(second: Observable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T2, TResult> combineLatest(second: Promise<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> combineLatest(second: IObservable<T2>, third: IObservable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> combineLatest(second: IObservable<T2>, third: Observable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> combineLatest(second: IObservable<T2>, third: Promise<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> combineLatest(second: Observable<T2>, third: IObservable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> combineLatest(second: Observable<T2>, third: Observable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> combineLatest(second: Observable<T2>, third: Promise<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> combineLatest(second: Promise<T2>, third: IObservable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> combineLatest(second: Promise<T2>, third: Observable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> combineLatest(second: Promise<T2>, third: Promise<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> combineLatest(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> combineLatest(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> combineLatest(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> combineLatest(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> combineLatest(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> combineLatest(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> combineLatest(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> combineLatest(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> combineLatest(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> combineLatest(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> combineLatest(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> combineLatest(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> combineLatest(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> combineLatest(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> combineLatest(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> combineLatest(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> combineLatest(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> combineLatest(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> combineLatest(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> combineLatest(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> combineLatest(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> combineLatest(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> combineLatest(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> combineLatest(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> combineLatest(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> combineLatest(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> combineLatest(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> combineLatest(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> combineLatest(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> combineLatest(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> combineLatest(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> combineLatest(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> combineLatest(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> combineLatest(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> combineLatest(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> combineLatest(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <TOther, TResult> combineLatest(souces: Array<dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */>, resultSelector: (firstValue: T, otherValues: TOther) -> TResult): Observable<TResult>
    fun concat(vararg sources: IObservable<T>): Observable<T>
    fun concat(vararg sources: Observable<T>): Observable<T>
    fun concat(vararg sources: Promise<T>): Observable<T>
    fun concatAll(): T
    fun merge(maxConcurrent: Number): T
    fun merge(other: IObservable<T>): Observable<T>
    fun merge(other: Observable<T>): Observable<T>
    fun merge(other: Promise<T>): Observable<T>
    fun mergeAll(): T
    fun onErrorResumeNext(second: IObservable<T>): Observable<T>
    fun onErrorResumeNext(second: Observable<T>): Observable<T>
    fun onErrorResumeNext(second: Promise<T>): Observable<T>
    fun <T2> skipUntil(other: IObservable<T2>): Observable<T>
    fun <T2> skipUntil(other: Observable<T2>): Observable<T>
    fun <T2> skipUntil(other: Promise<T2>): Observable<T>
    fun switch(): T
    fun switchLatest(): T
    fun <T2> takeUntil(other: IObservable<T2>): Observable<T>
    fun <T2> takeUntil(other: Observable<T2>): Observable<T>
    fun <T2> takeUntil(other: Promise<T2>): Observable<T>
    fun <T2, TResult> withLatestFrom(second: IObservable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T2, TResult> withLatestFrom(second: Observable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T2, TResult> withLatestFrom(second: Promise<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> withLatestFrom(second: IObservable<T2>, third: IObservable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> withLatestFrom(second: IObservable<T2>, third: Observable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> withLatestFrom(second: IObservable<T2>, third: Promise<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> withLatestFrom(second: Observable<T2>, third: IObservable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> withLatestFrom(second: Observable<T2>, third: Observable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> withLatestFrom(second: Observable<T2>, third: Promise<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> withLatestFrom(second: Promise<T2>, third: IObservable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> withLatestFrom(second: Promise<T2>, third: Observable<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, TResult> withLatestFrom(second: Promise<T2>, third: Promise<T3>, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> withLatestFrom(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> withLatestFrom(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> withLatestFrom(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> withLatestFrom(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> withLatestFrom(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> withLatestFrom(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> withLatestFrom(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> withLatestFrom(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, TResult> withLatestFrom(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> withLatestFrom(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> withLatestFrom(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> withLatestFrom(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> withLatestFrom(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> withLatestFrom(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> withLatestFrom(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> withLatestFrom(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> withLatestFrom(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> withLatestFrom(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> withLatestFrom(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> withLatestFrom(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> withLatestFrom(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> withLatestFrom(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> withLatestFrom(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> withLatestFrom(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> withLatestFrom(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> withLatestFrom(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> withLatestFrom(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> withLatestFrom(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> withLatestFrom(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> withLatestFrom(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> withLatestFrom(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> withLatestFrom(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> withLatestFrom(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> withLatestFrom(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> withLatestFrom(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> withLatestFrom(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> withLatestFrom(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> withLatestFrom(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> withLatestFrom(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> withLatestFrom(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> withLatestFrom(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> withLatestFrom(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> withLatestFrom(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> withLatestFrom(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> withLatestFrom(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> withLatestFrom(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> withLatestFrom(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> withLatestFrom(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> withLatestFrom(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> withLatestFrom(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> withLatestFrom(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> withLatestFrom(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> withLatestFrom(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> withLatestFrom(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <TOther, TResult> withLatestFrom(souces: Array<dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */>, resultSelector: (firstValue: T, otherValues: TOther) -> TResult): Observable<TResult>
    fun <T2, TResult> zip(second: IObservable<T2>, resultSelector: ((v1: T, v2: T2) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, TResult> zip(second: Observable<T2>, resultSelector: ((v1: T, v2: T2) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, TResult> zip(second: Promise<T2>, resultSelector: ((v1: T, v2: T2) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, TResult> zip(second: IObservable<T2>, third: IObservable<T3>, resultSelector: ((v1: T, v2: T2, v3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, TResult> zip(second: IObservable<T2>, third: Observable<T3>, resultSelector: ((v1: T, v2: T2, v3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, TResult> zip(second: IObservable<T2>, third: Promise<T3>, resultSelector: ((v1: T, v2: T2, v3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, TResult> zip(second: Observable<T2>, third: IObservable<T3>, resultSelector: ((v1: T, v2: T2, v3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, TResult> zip(second: Observable<T2>, third: Observable<T3>, resultSelector: ((v1: T, v2: T2, v3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, TResult> zip(second: Observable<T2>, third: Promise<T3>, resultSelector: ((v1: T, v2: T2, v3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, TResult> zip(second: Promise<T2>, third: IObservable<T3>, resultSelector: ((v1: T, v2: T2, v3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, TResult> zip(second: Promise<T2>, third: Observable<T3>, resultSelector: ((v1: T, v2: T2, v3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, TResult> zip(second: Promise<T2>, third: Promise<T3>, resultSelector: ((v1: T, v2: T2, v3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, TResult> zip(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, TResult> zip(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, TResult> zip(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, TResult> zip(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, TResult> zip(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, TResult> zip(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, TResult> zip(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, TResult> zip(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, TResult> zip(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> zip(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> zip(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> zip(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> zip(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> zip(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> zip(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> zip(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> zip(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, TResult> zip(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> zip(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> zip(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> zip(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> zip(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> zip(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> zip(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> zip(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> zip(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, TResult> zip(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> zip(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> zip(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> zip(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> zip(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> zip(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> zip(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> zip(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> zip(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, TResult> zip(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> zip(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> zip(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> zip(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> zip(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> zip(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> zip(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> zip(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> zip(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, TResult> zip(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(second: IObservable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(second: IObservable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(second: IObservable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(second: Observable<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(second: Observable<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(second: Observable<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(second: Promise<T2>, third: IObservable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(second: Promise<T2>, third: Observable<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(second: Promise<T2>, third: Promise<T3>, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> zip(souces: Array<dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */>, resultSelector: ((firstValue: T, otherValues: TOther) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun asObservable(): Observable<T>
    fun bufferWithCount(count: Number, skip: Number? = definedExternally /* null */): Observable<Array<T>>
    fun <TOrigin> dematerialize(): Observable<TOrigin>
    fun <TValue> distinctUntilChanged(keySelector: ((value: T) -> TValue)? = definedExternally /* null */, comparer: ((value1: TValue, value2: TValue) -> Boolean)? = definedExternally /* null */): Observable<T>
    fun `do`(observer: Observer<T>): Observable<T>
    fun tap(observer: Observer<T>): Observable<T>
    fun `do`(onNext: ((value: T) -> Unit)? = definedExternally /* null */, onError: ((exception: Any) -> Unit)? = definedExternally /* null */, onCompleted: (() -> Unit)? = definedExternally /* null */): Observable<T>
    fun tap(onNext: ((value: T) -> Unit)? = definedExternally /* null */, onError: ((exception: Any) -> Unit)? = definedExternally /* null */, onCompleted: (() -> Unit)? = definedExternally /* null */): Observable<T>
    fun doOnNext(onNext: (value: T) -> Unit, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun doOnNext(onNext: (value: T) -> Unit): Observable<T>
    fun doOnError(onError: (exception: Any) -> Unit, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun doOnCompleted(onCompleted: () -> Unit, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun tapOnNext(onNext: (value: T) -> Unit, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun tapOnError(onError: (exception: Any) -> Unit, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun tapOnCompleted(onCompleted: () -> Unit, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun finally(action: () -> Unit): Observable<T>
    fun ensure(action: () -> Unit): Observable<T>
    fun ignoreElements(): Observable<T>
    fun materialize(): Observable<T>
    fun repeat(repeatCount: Number? = definedExternally /* null */): Observable<T>
    fun retry(retryCount: Number? = definedExternally /* null */): Observable<T>
    fun retryWhen(notifier: (errors: Observable<Any>) -> Observable<Any>): Observable<T>
    fun <TAcc> scan(accumulator: (acc: TAcc, value: T) -> TAcc, seed: TAcc? = definedExternally /* null */): Observable<TAcc>
    fun scan(accumulator: (acc: T, value: T) -> T, seed: T? = definedExternally /* null */): Observable<T>
    fun skipLast(count: Number): Observable<T>
    fun startWith(vararg values: T): Observable<T>
    fun startWith(scheduler: IScheduler, vararg values: T): Observable<T>
    fun takeLast(count: Number): Observable<T>
    fun takeLastBuffer(count: Number): Observable<Array<T>>
    fun windowWithCount(count: Number, skip: Number? = definedExternally /* null */): Observable<Observable<T>>
    fun <TResult> concatMap(selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> concatMap(selector: Observable<TResult>): Observable<TResult>
    fun <TResult> concatMap(selector: Promise<TResult>): Observable<TResult>
    fun <TResult> concatMap(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> concatMap(selector: Array<TResult>): Observable<TResult>
    //    fun <TResult> concatMap(selector: `T$1`<T>): Observable<TResult>
    fun <TResult> concatMap(selector: (v1: T) -> Observable<TResult>): Observable<TResult>

    //    fun <TResult> concatMap(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
//    fun <TResult> concatMap(selector: (value: T) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> concatMap(selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>

    fun <TOther, TResult> concatMap(selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> concatMap(selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> concatMap(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> concatMap(selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> concatMap(selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> concatMap(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TResult> selectConcat(selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> selectConcat(selector: Observable<TResult>): Observable<TResult>
    fun <TResult> selectConcat(selector: Promise<TResult>): Observable<TResult>
    fun <TResult> selectConcat(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> selectConcat(selector: Array<TResult>): Observable<TResult>
    fun <TResult> selectConcat(selector: `T$1`<T>): Observable<TResult>
    fun <TResult> selectConcat(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> selectConcat(selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectConcat(selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectConcat(selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectConcat(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectConcat(selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectConcat(selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectConcat(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <T, TResult> concatMapObserver(onNext: (value: T, i: Number) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */, onError: (error: Any) -> dynamic /* Observable<Any> | IObservable<Any> | Promise<Any> */, onCompleted: () -> dynamic /* Observable<Any> | IObservable<Any> | Promise<Any> */, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <T, TResult> selectConcatObserver(onNext: (value: T, i: Number) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */, onError: (error: Any) -> dynamic /* Observable<Any> | IObservable<Any> | Promise<Any> */, onCompleted: () -> dynamic /* Observable<Any> | IObservable<Any> | Promise<Any> */, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun defaultIfEmpty(defaultValue: T? = definedExternally /* null */): Observable<T>
    fun <TKey> distinct(keySelector: ((value: T) -> TKey)? = definedExternally /* null */, keySerializer: ((key: TKey) -> String)? = definedExternally /* null */): Observable<T>
    fun <TKey, TDuration> groupByUntil(keySelector: (value: T) -> TKey, skipElementSelector: Boolean, durationSelector: (group: GroupedObservable<TKey, T>) -> Observable<TDuration>, keySerializer: ((key: TKey) -> String)? = definedExternally /* null */): Observable<GroupedObservable<TKey, T>>
    fun <TKey, TElement, TDuration> groupByUntil(keySelector: (value: T) -> TKey, elementSelector: (value: T) -> TElement, durationSelector: (group: GroupedObservable<TKey, TElement>) -> Observable<TDuration>, keySerializer: ((key: TKey) -> String)? = definedExternally /* null */): Observable<GroupedObservable<TKey, TElement>>
    fun <TKey, TElement> groupBy(keySelector: (value: T) -> TKey, skipElementSelector: Boolean? = definedExternally /* null */, keySerializer: ((key: TKey) -> String)? = definedExternally /* null */): Observable<GroupedObservable<TKey, T>>
    fun <TKey, TElement> groupBy(keySelector: (value: T) -> TKey, elementSelector: (value: T) -> TElement, keySerializer: ((key: TKey) -> String)? = definedExternally /* null */): Observable<GroupedObservable<TKey, TElement>>
    fun <TResult> select(selector: (value: T, index: Number, observable: Observable<T>) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TResult> map(selector: (value: T, index: Number, observable: Observable<T>) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TResult> map(selector: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TResult> map(selector: (value: T) -> TResult): Observable<TResult>
    fun <TResult> pluck(prop: String): Observable<TResult>
    fun <TResult> flatMap(selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> flatMap(selector: Observable<TResult>): Observable<TResult>
    fun <TResult> flatMap(selector: Promise<TResult>): Observable<TResult>
//    fun <TResult> flatMap(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> flatMap(selector: (value: T) -> Observable<TResult>): Observable<TResult>
    fun <TResult> flatMap(selector: Array<TResult>): Observable<TResult>
    fun <TResult> flatMap(selector: `T$1`<T>): Observable<TResult>
    //    fun <TResult> flatMap(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> flatMap(selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>

    fun <TOther, TResult> flatMap(selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMap(selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMap(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMap(selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMap(selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMap(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TResult> selectMany(selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> selectMany(selector: Observable<TResult>): Observable<TResult>
    fun <TResult> selectMany(selector: Promise<TResult>): Observable<TResult>
    fun <TResult> selectMany(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> selectMany(selector: Array<TResult>): Observable<TResult>
    fun <TResult> selectMany(selector: `T$1`<T>): Observable<TResult>
    fun <TResult> selectMany(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> selectMany(selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectMany(selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectMany(selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectMany(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectMany(selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectMany(selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectMany(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <T2, T3, T4> selectManyObserver(onNext: (value: T, index: Number) -> Observable<T2>, onError: (exception: Any) -> Observable<T3>, onCompleted: () -> Observable<T4>, thisArg: Any? = definedExternally /* null */): Observable<dynamic /* T2 | T3 | T4 */>
    fun <T2, T3, T4> flatMapObserver(onNext: (value: T, index: Number) -> Observable<T2>, onError: (exception: Any) -> Observable<T3>, onCompleted: () -> Observable<T4>, thisArg: Any? = definedExternally /* null */): Observable<dynamic /* T2 | T3 | T4 */>
    fun <TResult> selectSwitch(selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> selectSwitch(selector: Observable<TResult>): Observable<TResult>
    fun <TResult> selectSwitch(selector: Promise<TResult>): Observable<TResult>
    fun <TResult> selectSwitch(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> selectSwitch(selector: Array<TResult>): Observable<TResult>
    fun <TResult> selectSwitch(selector: `T$1`<T>): Observable<TResult>
    fun <TResult> selectSwitch(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> selectSwitch(selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitch(selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitch(selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitch(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitch(selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitch(selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitch(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TResult> flatMapLatest(selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> flatMapLatest(selector: Observable<TResult>): Observable<TResult>
    fun <TResult> flatMapLatest(selector: Promise<TResult>): Observable<TResult>
    fun <TResult> flatMapLatest(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> flatMapLatest(selector: Array<TResult>): Observable<TResult>
    fun <TResult> flatMapLatest(selector: `T$1`<T>): Observable<TResult>
    fun <TResult> flatMapLatest(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> flatMapLatest(selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapLatest(selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapLatest(selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapLatest(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapLatest(selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapLatest(selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapLatest(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun skip(count: Number): Observable<T>
    fun skipWhile(predicate: (value: T, index: Number, observable: Observable<T>) -> Boolean, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun take(count: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun takeWhile(predicate: (value: T) -> Boolean, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun takeWhile(predicate: (value: T, index: Number, observable: Observable<T>) -> Boolean, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun where(predicate: (value: T, index: Number, observable: Observable<T>) -> Boolean, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun filter(predicate: (value: T, index: Number, observable: Observable<T>) -> Boolean, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun filter(predicate: (value: T) -> Boolean): Observable<T>
    fun <TAcc> reduce(accumulator: (acc: TAcc, value: T) -> TAcc, seed: TAcc? = definedExternally /* null */): Observable<TAcc>
    fun reduce(accumulator: (acc: T, value: T) -> T, seed: T? = definedExternally /* null */): Observable<T>
    fun some(predicate: ((value: T, index: Number, observable: Observable<T>) -> Boolean)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Observable<Boolean>
    fun isEmpty(): Observable<Boolean>
    fun every(predicate: ((value: T, index: Number, observable: Observable<T>) -> Boolean)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Observable<Boolean>
    fun includes(value: T, comparer: ((value1: T, value2: T) -> Boolean)? = definedExternally /* null */): Observable<Boolean>
    fun count(predicate: ((value: T, index: Number, observable: Observable<T>) -> Boolean)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Observable<Number>
    fun indexOf(element: T, fromIndex: Number? = definedExternally /* null */): Observable<Number>
    fun sum(keySelector: ((value: T, index: Number, observable: Observable<T>) -> Number)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Observable<Number>
    fun <TKey> minBy(keySelector: (item: T) -> TKey, comparer: (value1: TKey, value2: TKey) -> Number): Observable<T>
    fun minBy(keySelector: (item: T) -> Number): Observable<T>
    fun min(comparer: ((value1: T, value2: T) -> Number)? = definedExternally /* null */): Observable<Number>
    fun <TKey> maxBy(keySelector: (item: T) -> TKey, comparer: (value1: TKey, value2: TKey) -> Number): Observable<T>
    fun maxBy(keySelector: (item: T) -> Number): Observable<T>
    fun max(comparer: ((value1: T, value2: T) -> Number)? = definedExternally /* null */): Observable<Number>
    fun average(keySelector: ((value: T, index: Number, observable: Observable<T>) -> Number)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Observable<Number>
    fun sequenceEqual(second: IObservable<T>, comparer: ((value1: T, value2: T) -> Boolean)? = definedExternally /* null */): Observable<Boolean>
    fun sequenceEqual(second: Observable<T>, comparer: ((value1: T, value2: T) -> Boolean)? = definedExternally /* null */): Observable<Boolean>
    fun sequenceEqual(second: Array<T>, comparer: ((value1: T, value2: T) -> Boolean)? = definedExternally /* null */): Observable<Boolean>
    fun sequenceEqual(second: Promise<T>, comparer: ((value1: T, value2: T) -> Boolean)? = definedExternally /* null */): Observable<Boolean>
    fun sequenceEqual(second: `T$1`<T>, comparer: ((value1: T, value2: T) -> Boolean)? = definedExternally /* null */): Observable<Boolean>
    fun <TOther> sequenceEqual(second: IObservable<T>, comparer: (value1: dynamic /* T | TOther */, value2: dynamic /* T | TOther */) -> Boolean): Observable<Boolean>
    fun <TOther> sequenceEqual(second: Observable<T>, comparer: (value1: dynamic /* T | TOther */, value2: dynamic /* T | TOther */) -> Boolean): Observable<Boolean>
    fun <TOther> sequenceEqual(second: Array<T>, comparer: (value1: dynamic /* T | TOther */, value2: dynamic /* T | TOther */) -> Boolean): Observable<Boolean>
    fun <TOther> sequenceEqual(second: Promise<T>, comparer: (value1: dynamic /* T | TOther */, value2: dynamic /* T | TOther */) -> Boolean): Observable<Boolean>
    fun <TOther> sequenceEqual(second: `T$1`<T>, comparer: (value1: dynamic /* T | TOther */, value2: dynamic /* T | TOther */) -> Boolean): Observable<Boolean>
    fun elementAt(index: Number): Observable<T>
    fun single(predicate: ((value: T, index: Number, observable: Observable<T>) -> Boolean)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun first(predicate: ((value: T, index: Number, observable: Observable<T>) -> Boolean)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun last(predicate: ((value: T, index: Number, observable: Observable<T>) -> Boolean)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun find(predicate: (value: T, index: Number, observable: Observable<T>) -> Boolean, thisArg: Any? = definedExternally /* null */): Observable<T>
    fun findIndex(predicate: (value: T, index: Number, observable: Observable<T>) -> Boolean, thisArg: Any? = definedExternally /* null */): Observable<Number>
    fun pausable(pauser: Observable<Boolean>? = definedExternally /* null */): PausableObservable<T>
    fun pausableBuffered(pauser: Observable<Boolean>? = definedExternally /* null */): PausableObservable<T>
    fun controlled(enableQueue: Boolean? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): ControlledObservable<T>
    fun <TDest> pipe(dest: TDest): TDest
    fun multicast(subject: ISubject<T>): ConnectableObservable<T>
    fun multicast(subject: () -> ISubject<T>): ConnectableObservable<T>
    fun <TResult> multicast(subjectSelector: ISubject<T>, selector: (source: ConnectableObservable<T>) -> Observable<T>): Observable<T>
    fun <TResult> multicast(subjectSelector: () -> ISubject<T>, selector: (source: ConnectableObservable<T>) -> Observable<T>): Observable<T>
    fun publish(): ConnectableObservable<T>
    fun <TResult> publish(selector: (source: ConnectableObservable<T>) -> Observable<TResult>): Observable<TResult>
    fun share(): Observable<T>
    fun publishLast(): ConnectableObservable<T>
    fun <TResult> publishLast(selector: (source: ConnectableObservable<T>) -> Observable<TResult>): Observable<TResult>
    fun publishValue(initialValue: T): ConnectableObservable<T>
    fun <TResult> publishValue(selector: (source: ConnectableObservable<T>) -> Observable<TResult>, initialValue: T): Observable<TResult>
    fun shareValue(initialValue: T): Observable<T>
    fun replay(selector: Unit? = definedExternally /* null */, bufferSize: Number? = definedExternally /* null */, window: Number? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): ConnectableObservable<T>
    fun replay(selector: (source: ConnectableObservable<T>) -> Observable<T>, bufferSize: Number? = definedExternally /* null */, window: Number? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun shareReplay(bufferSize: Number? = definedExternally /* null */, window: Number? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun singleInstance(): Observable<T>
    fun <TRight, TDurationLeft, TDurationRight, TResult> join(right: Observable<TRight>, leftDurationSelector: (leftItem: T) -> Observable<TDurationLeft>, rightDurationSelector: (rightItem: TRight) -> Observable<TDurationRight>, resultSelector: (leftItem: T, rightItem: TRight) -> TResult): Observable<TResult>
    fun <TRight, TDurationLeft, TDurationRight, TResult> groupJoin(right: Observable<TRight>, leftDurationSelector: (leftItem: T) -> Observable<TDurationLeft>, rightDurationSelector: (rightItem: TRight) -> Observable<TDurationRight>, resultSelector: (leftItem: T, rightItem: Observable<TRight>) -> TResult): Observable<TResult>
    fun <TBufferOpening> buffer(bufferOpenings: Observable<TBufferOpening>): Observable<Array<T>>
    fun <TBufferClosing> buffer(bufferClosingSelector: () -> Observable<TBufferClosing>): Observable<Array<T>>
    fun <TBufferOpening, TBufferClosing> buffer(bufferOpenings: Observable<TBufferOpening>, bufferClosingSelector: () -> Observable<TBufferClosing>): Observable<Array<T>>
    fun <TWindowOpening> window(windowOpenings: Observable<TWindowOpening>): Observable<Observable<T>>
    fun <TWindowClosing> window(windowClosingSelector: () -> Observable<TWindowClosing>): Observable<Observable<T>>
    fun <TWindowOpening, TWindowClosing> window(windowOpenings: Observable<TWindowOpening>, windowClosingSelector: () -> Observable<TWindowClosing>): Observable<Observable<T>>
    fun pairwise(): Observable<dynamic /* JsTuple<T, T> */>
    fun partition(predicate: (value: T, index: Number, observable: Observable<T>) -> Boolean, thisArg: Any? = definedExternally /* null */): dynamic /* JsTuple<Observable<T>, Observable<T>> */
    fun <TResult> let(selector: (source: Observable<T>) -> Observable<TResult>): Observable<TResult>
    fun doWhile(condition: () -> Boolean): Observable<T>
    fun expand(selector: (item: T) -> Observable<T>, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <TSecond, TResult> forkJoin(second: IObservable<TSecond>, resultSelector: (left: T, right: TSecond) -> TResult): Observable<TResult>
    fun <TSecond, TResult> forkJoin(second: Observable<TSecond>, resultSelector: (left: T, right: TSecond) -> TResult): Observable<TResult>
    fun <TSecond, TResult> forkJoin(second: Promise<TSecond>, resultSelector: (left: T, right: TSecond) -> TResult): Observable<TResult>
    fun <TResult> manySelect(selector: (value: Observable<T>, index: Number, observable: Observable<Observable<T>>) -> TResult, scheduler: IScheduler? = definedExternally /* null */): Observable<TResult>
    fun <TResult> extend(selector: (value: Observable<T>, index: Number, observable: Observable<Observable<T>>) -> TResult, scheduler: IScheduler? = definedExternally /* null */): Observable<TResult>
    fun <T2> and(right: Observable<T2>): Pattern2<T, T2>
    fun <TR> thenDo(selector: (item1: T) -> TR): Plan<TR>
    fun delay(dueTime: Date, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun delay(dueTime: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun delay(delayDurationSelector: (item: T) -> dynamic /* Observable<Number> | IObservable<Number> | Promise<Number> */): Observable<T>
    fun delay(subscriptionDelay: Observable<Number>, delayDurationSelector: (item: T) -> dynamic /* Observable<Number> | IObservable<Number> | Promise<Number> */): Observable<T>
    fun debounce(dueTime: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun debounce(debounceDurationSelector: (item: T) -> dynamic /* Observable<Any> | IObservable<Any> | Promise<Any> */): Observable<T>
    fun windowWithTime(timeSpan: Number, timeShift: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Observable<T>>
    fun windowWithTime(timeSpan: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Observable<T>>
    fun windowWithTimeOrCount(timeSpan: Number, count: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Observable<T>>
    fun bufferWithTime(timeSpan: Number, timeShift: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Array<T>>
    fun bufferWithTime(timeSpan: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Array<T>>
    fun bufferWithTimeOrCount(timeSpan: Number, count: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Array<T>>
    fun timeInterval(scheduler: IScheduler? = definedExternally /* null */): Observable<TimeInterval<T>>
    fun timestamp(scheduler: IScheduler? = definedExternally /* null */): Observable<Timestamp<T>>
    fun sample(intervalOrSampler: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <TSample> sample(sampler: Observable<TSample>, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun throttleLatest(interval: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <TSample> throttleLatest(sampler: Observable<TSample>, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun timeout(dueTime: Date, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun timeout(dueTime: Date, other: Observable<T>? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun timeout(dueTime: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun timeout(dueTime: Number, other: Observable<T>? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <TTimeout> timeout(timeoutdurationSelector: (item: T) -> Observable<TTimeout>): Observable<T>
    fun <TTimeout> timeout(timeoutdurationSelector: (item: T) -> Observable<TTimeout>, other: Observable<T>): Observable<T>
    fun <TTimeout> timeout(firstTimeout: Observable<TTimeout>, timeoutdurationSelector: (item: T) -> Observable<TTimeout>, other: Observable<T>? = definedExternally /* null */): Observable<T>
    fun delaySubscription(dueTime: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun skipLastWithTime(duration: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun takeLastWithTime(duration: Number, timerScheduler: IScheduler? = definedExternally /* null */, loopScheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun takeLastBufferWithTime(duration: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Array<T>>
    fun takeWithTime(duration: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun skipWithTime(duration: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun skipUntilWithTime(startTime: Date, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun skipUntilWithTime(duration: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun takeUntilWithTime(endTime: Date, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun takeUntilWithTime(duration: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun throttle(windowDuration: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun transduce(transducer: Any): Any
    fun switchFirst(): T
    fun <TResult> selectSwitchFirst(selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> selectSwitchFirst(selector: Observable<TResult>): Observable<TResult>
    fun <TResult> selectSwitchFirst(selector: Promise<TResult>): Observable<TResult>
    fun <TResult> selectSwitchFirst(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> selectSwitchFirst(selector: Array<TResult>): Observable<TResult>
    fun <TResult> selectSwitchFirst(selector: `T$1`<T>): Observable<TResult>
    fun <TResult> selectSwitchFirst(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> selectSwitchFirst(selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitchFirst(selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitchFirst(selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitchFirst(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitchFirst(selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitchFirst(selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectSwitchFirst(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TResult> flatMapFirst(selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> flatMapFirst(selector: Observable<TResult>): Observable<TResult>
    fun <TResult> flatMapFirst(selector: Promise<TResult>): Observable<TResult>
    fun <TResult> flatMapFirst(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> flatMapFirst(selector: Array<TResult>): Observable<TResult>
    fun <TResult> flatMapFirst(selector: `T$1`<T>): Observable<TResult>
    fun <TResult> flatMapFirst(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> flatMapFirst(selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapFirst(selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapFirst(selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapFirst(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapFirst(selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapFirst(selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapFirst(selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: Observable<TResult>): Observable<TResult>
    fun <TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: Promise<TResult>): Observable<TResult>
    fun <TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: Array<TResult>): Observable<TResult>
    fun <TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: `T$1`<T>): Observable<TResult>
    fun <TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> selectManyWithMaxConcurrent(maxConcurrent: Number, selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: IObservable<TResult>): Observable<TResult>
    fun <TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: Observable<TResult>): Observable<TResult>
    fun <TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: Promise<TResult>): Observable<TResult>
    fun <TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TResult> | Observable<TResult> | Promise<TResult> */): Observable<TResult>
    fun <TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: Array<TResult>): Observable<TResult>
    fun <TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: `T$1`<T>): Observable<TResult>
    fun <TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TResult> | `T$1`<T> */): Observable<TResult>
    fun <TOther, TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: IObservable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: Observable<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: Promise<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: Array<TOther>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: `T$1`<T>, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <TOther, TResult> flatMapWithMaxConcurrent(maxConcurrent: Number, selector: (value: T, index: Number, observable: Observable<T>) -> dynamic /* Array<TOther> | `T$1`<T> */, resultSelector: (value: T, selectorValue: TOther, index: Number, selectorOther: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun timeout(dueTime: Date): Observable<T>
    fun timeout(dueTime: Number): Observable<T>
}

external interface IDisposable {
    fun dispose()
}

external interface Disposable : IDisposable {
    var isDisposed: Boolean? get() = definedExternally; set(value) = definedExternally

    companion object : DisposableStatic by definedExternally {
    }
}

external interface DisposableStatic {
    fun create(action: () -> Unit): Disposable
    var empty: IDisposable
    fun isDisposable(d: Any): Boolean
}

external interface ObservableStatic {
    fun isObservable(o: Any): Boolean
    fun <T> fromPromise(promise: Promise<T>): Observable<T>
    fun <T> create(subscribe: (observer: Observer<T>) -> dynamic /* Unit | Function<*> | IDisposable */): Observable<T>
    fun <T> defer(observableFactory: () -> dynamic /* IObservable<T> | Observable<T> | Promise<T> */): Observable<T>
    fun <T> empty(scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <T> from(array: Array<T>): Observable<T>
    fun <T> from(array: `T$1`<T>): Observable<T>
    fun <T, TResult> from(array: Array<T>, mapFn: (value: T, index: Number) -> TResult, thisArg: Any? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): Observable<TResult>
    fun <T, TResult> from(array: `T$1`<T>, mapFn: (value: T, index: Number) -> TResult, thisArg: Any? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): Observable<TResult>
    fun <T> fromArray(array: Array<T>, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <T> fromArray(array: `T$1`<T>, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <TState, TResult> generate(initialState: TState, condition: (state: TState) -> Boolean, iterate: (state: TState) -> TState, resultSelector: (state: TState) -> TResult, scheduler: IScheduler? = definedExternally /* null */): Observable<TResult>
    fun <T> of(vararg values: T): Observable<T>
    fun <T> ofWithScheduler(scheduler: IScheduler? = definedExternally /* null */, vararg values: T): Observable<T>
    fun <T> ofArrayChanges(obj: Array<T>): Observable<ArrayObserveChange<T>>
    fun <T> ofObjectChanges(obj: T): Observable<ObjectObserveChange<T>>
    fun <T> never(): Observable<T>
    fun <T> pairs(obj: `T$2`<T>, scheduler: IScheduler? = definedExternally /* null */): Observable<dynamic /* JsTuple<String, T> */>
    fun <T> pairs(obj: `T$3`<T>, scheduler: IScheduler? = definedExternally /* null */): Observable<dynamic /* JsTuple<Number, T> */>
    fun range(start: Number, count: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Number>
    fun <T> repeat(value: T, repeatCount: Number? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <T> repeat(value: T, repeatCount: Unit? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <T> `return`(value: T, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <T> just(value: T, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <T> `throw`(exception: Error, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <T> `throw`(exception: Any, scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    fun <TSource, TResource : IDisposable> using(resourceFactory: () -> TResource, observableFactory: (resource: TResource) -> Observable<TSource>): Observable<TSource>
    fun <T> amb(observables: Array<dynamic /* IObservable<T> | Observable<T> | Promise<T> */>): Observable<T>
    fun <T> amb(vararg observables: IObservable<T>): Observable<T>
    fun <T> amb(vararg observables: Observable<T>): Observable<T>
    fun <T> amb(vararg observables: Promise<T>): Observable<T>
    fun <T> catch(sources: Array<dynamic /* IObservable<T> | Observable<T> | Promise<T> */>): Observable<T>
    fun <T> catch(vararg sources: IObservable<T>): Observable<T>
    fun <T> catch(vararg sources: Observable<T>): Observable<T>
    fun <T> catch(vararg sources: Promise<T>): Observable<T>
    fun <T, T2, TResult> combineLatest(first: IObservable<T>, second: IObservable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T, T2, TResult> combineLatest(first: IObservable<T>, second: Observable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T, T2, TResult> combineLatest(first: IObservable<T>, second: Promise<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T, T2, TResult> combineLatest(first: Observable<T>, second: IObservable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T, T2, TResult> combineLatest(first: Observable<T>, second: Observable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T, T2, TResult> combineLatest(first: Observable<T>, second: Promise<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T, T2, TResult> combineLatest(first: Promise<T>, second: IObservable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T, T2, TResult> combineLatest(first: Promise<T>, second: Observable<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T, T2, TResult> combineLatest(first: Promise<T>, second: Promise<T2>, resultSelector: (v1: T, v2: T2) -> TResult): Observable<TResult>
    fun <T, T2, T3, TResult> combineLatest(first: IObservable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T, T2, T3, TResult> combineLatest(first: IObservable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T, T2, T3, TResult> combineLatest(first: IObservable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T, T2, T3, TResult> combineLatest(first: Observable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T, T2, T3, TResult> combineLatest(first: Observable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T, T2, T3, TResult> combineLatest(first: Observable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T, T2, T3, TResult> combineLatest(first: Promise<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T, T2, T3, TResult> combineLatest(first: Promise<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T, T2, T3, TResult> combineLatest(first: Promise<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: (v1: T, v2: T2, v3: T3) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, TResult> combineLatest(first: IObservable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, TResult> combineLatest(first: IObservable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, TResult> combineLatest(first: IObservable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, TResult> combineLatest(first: Observable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, TResult> combineLatest(first: Observable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, TResult> combineLatest(first: Observable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, TResult> combineLatest(first: Promise<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, TResult> combineLatest(first: Promise<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, TResult> combineLatest(first: Promise<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, TResult> combineLatest(first: IObservable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, TResult> combineLatest(first: IObservable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, TResult> combineLatest(first: IObservable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, TResult> combineLatest(first: Observable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, TResult> combineLatest(first: Observable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, TResult> combineLatest(first: Observable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, TResult> combineLatest(first: Promise<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, TResult> combineLatest(first: Promise<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, TResult> combineLatest(first: Promise<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, TResult> combineLatest(first: IObservable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, TResult> combineLatest(first: IObservable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, TResult> combineLatest(first: IObservable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, TResult> combineLatest(first: Observable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, TResult> combineLatest(first: Observable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, TResult> combineLatest(first: Observable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, TResult> combineLatest(first: Promise<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, TResult> combineLatest(first: Promise<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, TResult> combineLatest(first: Promise<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, TResult> combineLatest(first: IObservable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, eventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, TResult> combineLatest(first: IObservable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, eventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, TResult> combineLatest(first: IObservable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, eventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, TResult> combineLatest(first: Observable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, eventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, TResult> combineLatest(first: Observable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, eventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, TResult> combineLatest(first: Observable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, eventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, TResult> combineLatest(first: Promise<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, eventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, TResult> combineLatest(first: Promise<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, eventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, TResult> combineLatest(first: Promise<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, eventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(first: IObservable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(first: IObservable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(first: IObservable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(first: Observable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(first: Observable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(first: Observable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(first: Promise<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(first: Promise<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, TResult> combineLatest(first: Promise<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(first: IObservable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(first: IObservable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(first: IObservable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(first: Observable<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(first: Observable<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(first: Observable<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(first: Promise<T>, second: IObservable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(first: Promise<T>, second: Observable<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <T, T2, T3, T4, T5, T6, T7, T8, T9, TResult> combineLatest(first: Promise<T>, second: Promise<T2>, third: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, fourth: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, fifth: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, sixth: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, seventh: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, eighth: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, ninth: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: (v1: T, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) -> TResult): Observable<TResult>
    fun <TOther, TResult> combineLatest(souces: Array<dynamic /* IObservable<TOther> | Observable<TOther> | Promise<TOther> */>, resultSelector: (otherValues: TOther) -> TResult): Observable<TResult>
    fun <T> concat(vararg sources: IObservable<T>): Observable<T>
    fun <T> concat(vararg sources: Observable<T>): Observable<T>
    fun <T> concat(vararg sources: Promise<T>): Observable<T>
//    fun <T> concat(sources: Array<dynamic /* IObservable<T> | Observable<T> | Promise<T> */>): Observable<T>
    fun <T> concat(sources: Array<IObservable<T>>): Observable<T>
    fun <T> merge(vararg sources: IObservable<T>): Observable<T>
    fun <T> merge(vararg sources: Observable<T>): Observable<T>
    fun <T> merge(vararg sources: Promise<T>): Observable<T>
    fun <T> merge(sources: Array<dynamic /* IObservable<T> | Observable<T> | Promise<T> */>): Observable<T>
    fun <T> merge(scheduler: IScheduler, vararg sources: IObservable<T>): Observable<T>
    fun <T> merge(scheduler: IScheduler, vararg sources: Observable<T>): Observable<T>
    fun <T> merge(scheduler: IScheduler, vararg sources: Promise<T>): Observable<T>
    fun <T> merge(scheduler: IScheduler, sources: Array<dynamic /* IObservable<T> | Observable<T> | Promise<T> */>): Observable<T>
    fun <T> mergeDelayError(vararg sources: IObservable<T>): Observable<T>
    fun <T> mergeDelayError(vararg sources: Observable<T>): Observable<T>
    fun <T> mergeDelayError(vararg sources: Promise<T>): Observable<T>
    fun <T> mergeDelayError(sources: Array<dynamic /* IObservable<T> | Observable<T> | Promise<T> */>): Observable<T>
    fun <T> onErrorResumeNext(vararg sources: IObservable<T>): Observable<T>
    fun <T> onErrorResumeNext(vararg sources: Observable<T>): Observable<T>
    fun <T> onErrorResumeNext(vararg sources: Promise<T>): Observable<T>
    fun <T> onErrorResumeNext(sources: Array<dynamic /* IObservable<T> | Observable<T> | Promise<T> */>): Observable<T>
    fun <T1, T2, TResult> zip(sources: Array<dynamic /* IObservable<T2> | Observable<T2> | Promise<T2> */>, resultSelector: ((item1: T1, right: T2) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, TResult> zip(source1: IObservable<T1>, ObservableOrPromise: Observable<T2>, resultSelector: ((item1: T1, item2: T2) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, TResult> zip(source1: Observable<T1>, ObservableOrPromise: Observable<T2>, resultSelector: ((item1: T1, item2: T2) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, TResult> zip(source1: Promise<T1>, ObservableOrPromise: Observable<T2>, resultSelector: ((item1: T1, item2: T2) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, TResult> zip(source1: IObservable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: ((item1: T1, item2: T2, item3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, TResult> zip(source1: IObservable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: ((item1: T1, item2: T2, item3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, TResult> zip(source1: IObservable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: ((item1: T1, item2: T2, item3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, TResult> zip(source1: Observable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: ((item1: T1, item2: T2, item3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, TResult> zip(source1: Observable<T1>, source2: Observable<T2>, source3: IObservable<T3>, resultSelector: ((item1: T1, item2: T2, item3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, TResult> zip(source1: Observable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: ((item1: T1, item2: T2, item3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, TResult> zip(source1: Promise<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: ((item1: T1, item2: T2, item3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, TResult> zip(source1: Promise<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: ((item1: T1, item2: T2, item3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, TResult> zip(source1: Promise<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, resultSelector: ((item1: T1, item2: T2, item3: T3) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, TResult> zip(source1: Observable<T1>, source2: IObservable<T2>, source3: IObservable<T3>, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, TResult> zip(source1: Observable<T1>, source2: IObservable<T2>, source3: Observable<T3>, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, TResult> zip(source1: Observable<T1>, source2: IObservable<T2>, source3: Promise<T3>, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, TResult> zip(source1: Observable<T1>, source2: Observable<T2>, source3: IObservable<T3>, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, TResult> zip(source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, TResult> zip(source1: Observable<T1>, source2: Observable<T2>, source3: Promise<T3>, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, TResult> zip(source1: Observable<T1>, source2: Promise<T2>, source3: IObservable<T3>, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, TResult> zip(source1: Observable<T1>, source2: Promise<T2>, source3: Observable<T3>, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, TResult> zip(source1: Observable<T1>, source2: Promise<T2>, source3: Promise<T3>, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, TResult> zip(source1: IObservable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, TResult> zip(source1: IObservable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, TResult> zip(source1: IObservable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, TResult> zip(source1: Observable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, TResult> zip(source1: Observable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, TResult> zip(source1: Observable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, TResult> zip(source1: Promise<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, TResult> zip(source1: Promise<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, TResult> zip(source1: Promise<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, TResult> zip(source1: IObservable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, TResult> zip(source1: IObservable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, TResult> zip(source1: IObservable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, TResult> zip(source1: Observable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, TResult> zip(source1: Observable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, TResult> zip(source1: Observable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, TResult> zip(source1: Promise<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, TResult> zip(source1: Promise<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, TResult> zip(source1: Promise<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, TResult> zip(source1: IObservable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, TResult> zip(source1: IObservable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, TResult> zip(source1: IObservable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, TResult> zip(source1: Observable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, TResult> zip(source1: Observable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, TResult> zip(source1: Observable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, TResult> zip(source1: Promise<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, TResult> zip(source1: Promise<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, TResult> zip(source1: Promise<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, TResult> zip(source1: IObservable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, TResult> zip(source1: IObservable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, TResult> zip(source1: IObservable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, TResult> zip(source1: Observable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, TResult> zip(source1: Observable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, TResult> zip(source1: Observable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, TResult> zip(source1: Promise<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, TResult> zip(source1: Promise<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, TResult> zip(source1: Promise<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(source1: IObservable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, source9: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(source1: IObservable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, source9: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(source1: IObservable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, source9: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(source1: Observable<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, source9: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(source1: Observable<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, source9: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(source1: Observable<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, source9: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(source1: Promise<T1>, source2: IObservable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, source9: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(source1: Promise<T1>, source2: Observable<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, source9: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, TResult> zip(source1: Promise<T1>, source2: Promise<T2>, source3: dynamic /* IObservable<T3> | Observable<T3> | Promise<T3> */, source4: dynamic /* IObservable<T4> | Observable<T4> | Promise<T4> */, source5: dynamic /* IObservable<T5> | Observable<T5> | Promise<T5> */, source6: dynamic /* IObservable<T6> | Observable<T6> | Promise<T6> */, source7: dynamic /* IObservable<T7> | Observable<T7> | Promise<T7> */, source8: dynamic /* IObservable<T8> | Observable<T8> | Promise<T8> */, source9: dynamic /* IObservable<T9> | Observable<T9> | Promise<T9> */, resultSelector: ((item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TResult)? = definedExternally /* null */): Observable<TResult>
    fun <T> zipIterable(vararg sources: Observable<T>): Observable<Array<T>>
    fun <T> zipIterable(sources: Array<Observable<T>>): Observable<Array<T>>
    fun <T> wrap(fn: Function<*>): Observable<T>
    fun <T> spawn(fn: Function<*>): Observable<T>
    fun <T> start(func: () -> T, scheduler: IScheduler? = definedExternally /* null */, context: Any? = definedExternally /* null */): Observable<T>
    fun <TResult> toAsync(func: () -> TResult, context: Any? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): () -> Observable<TResult>
    fun <T1, TResult> toAsync(func: (arg1: T1) -> TResult, context: Any? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): (arg1: T1) -> Observable<TResult>
    fun <T1, T2, TResult> toAsync(func: (arg1: T1, arg2: T2) -> TResult, context: Any? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): (arg1: T1, arg2: T2) -> Observable<TResult>
    fun <T1, T2, T3, TResult> toAsync(func: (arg1: T1, arg2: T2, arg3: T3) -> TResult, context: Any? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3) -> Observable<TResult>
    fun <T1, T2, T3, T4, TResult> toAsync(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4) -> TResult, context: Any? = definedExternally /* null */, scheduler: IScheduler? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4) -> Observable<TResult>
    fun <TResult> fromCallback(func: Function<*>, context: Any, selector: Function<*>): (args: Any) -> Observable<TResult>
    fun <TResult, T1> fromCallback(func: (arg1: T1, callback: (result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1) -> Observable<TResult>
    fun <TResult, T1, T2> fromCallback(func: (arg1: T1, arg2: T2, callback: (result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2) -> Observable<TResult>
    fun <TResult, T1, T2, T3> fromCallback(func: (arg1: T1, arg2: T2, arg3: T3, callback: (result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4> fromCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, callback: (result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5> fromCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, callback: (result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5, T6> fromCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, callback: (result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5, T6, T7> fromCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, callback: (result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5, T6, T7, T8> fromCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, callback: (result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5, T6, T7, T8, T9> fromCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, callback: (result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9) -> Observable<TResult>
    fun <TResult> fromNodeCallback(func: Function<*>, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (args: Any) -> Observable<TResult>
    fun <TResult, T1> fromNodeCallback(func: (arg1: T1, callback: (err: Any, result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1) -> Observable<TResult>
    fun <TResult, T1, T2> fromNodeCallback(func: (arg1: T1, arg2: T2, callback: (err: Any, result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2) -> Observable<TResult>
    fun <TResult, T1, T2, T3> fromNodeCallback(func: (arg1: T1, arg2: T2, arg3: T3, callback: (err: Any, result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4> fromNodeCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, callback: (err: Any, result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5> fromNodeCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, callback: (err: Any, result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5, T6> fromNodeCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, callback: (err: Any, result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5, T6, T7> fromNodeCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, callback: (err: Any, result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5, T6, T7, T8> fromNodeCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, callback: (err: Any, result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8) -> Observable<TResult>
    fun <TResult, T1, T2, T3, T4, T5, T6, T7, T8, T9> fromNodeCallback(func: (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, callback: (err: Any, result: TResult) -> Any) -> Any, context: Any? = definedExternally /* null */, selector: Function<*>? = definedExternally /* null */): (arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9) -> Observable<TResult>
    fun <T> fromEvent(element: EventTarget, eventName: String, selector: ((arguments: Array<Any>) -> T)? = definedExternally /* null */): Observable<T>
    fun <T> fromEvent(element: EventTarget, eventName: String): Observable<T>
    fun <T> fromEvent(element: `T$4`, eventName: String, selector: ((arguments: Array<Any>) -> T)? = definedExternally /* null */): Observable<T>
    fun <T> fromEventPattern(addHandler: (handler: Function<*>) -> Unit, removeHandler: (handler: Function<*>) -> Unit, selector: ((arguments: Array<Any>) -> T)? = definedExternally /* null */): Observable<T>
    fun <T> startAsync(functionAsync: () -> Promise<T>): Observable<T>
    fun <T> `if`(condition: () -> Boolean, thenSource: IObservable<T>, elseSourceOrScheduler: dynamic /* IScheduler? | IObservable<T>? | Observable<T>? | Promise<T>? */ = definedExternally /* null */): Observable<T>
    fun <T> `if`(condition: () -> Boolean, thenSource: Observable<T>, elseSourceOrScheduler: dynamic /* IScheduler? | IObservable<T>? | Observable<T>? | Promise<T>? */ = definedExternally /* null */): Observable<T>
    fun <T> `if`(condition: () -> Boolean, thenSource: Promise<T>, elseSourceOrScheduler: dynamic /* IScheduler? | IObservable<T>? | Observable<T>? | Promise<T>? */ = definedExternally /* null */): Observable<T>
    fun <T, TResult> `for`(sources: Array<T>, resultSelector: (value: T, index: Number, observable: Observable<T>) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <T, TResult> forIn(sources: Array<T>, resultSelector: (value: T, index: Number, observable: Observable<T>) -> TResult, thisArg: Any? = definedExternally /* null */): Observable<TResult>
    fun <T> `while`(condition: () -> Boolean, source: IObservable<T>): Observable<T>
    fun <T> `while`(condition: () -> Boolean, source: Observable<T>): Observable<T>
    fun <T> `while`(condition: () -> Boolean, source: Promise<T>): Observable<T>
    fun <T> whileDo(condition: () -> Boolean, source: IObservable<T>): Observable<T>
    fun <T> whileDo(condition: () -> Boolean, source: Observable<T>): Observable<T>
    fun <T> whileDo(condition: () -> Boolean, source: Promise<T>): Observable<T>
    fun <T> case(selector: () -> String, sources: `T$5`, schedulerOrElseSource: IScheduler? = definedExternally /* null */): Observable<T>
    fun <T> case(selector: () -> String, sources: `T$5`, schedulerOrElseSource: IObservable<T>? = definedExternally /* null */): Observable<T>
    fun <T> case(selector: () -> String, sources: `T$5`, schedulerOrElseSource: Observable<T>? = definedExternally /* null */): Observable<T>
    fun <T> case(selector: () -> String, sources: `T$5`, schedulerOrElseSource: Promise<T>? = definedExternally /* null */): Observable<T>
    fun <T> case(selector: () -> Number, sources: `T$6`, schedulerOrElseSource: IScheduler? = definedExternally /* null */): Observable<T>
    fun <T> case(selector: () -> Number, sources: `T$6`, schedulerOrElseSource: IObservable<T>? = definedExternally /* null */): Observable<T>
    fun <T> case(selector: () -> Number, sources: `T$6`, schedulerOrElseSource: Observable<T>? = definedExternally /* null */): Observable<T>
    fun <T> case(selector: () -> Number, sources: `T$6`, schedulerOrElseSource: Promise<T>? = definedExternally /* null */): Observable<T>
    fun <T> forkJoin(sources: Array<dynamic /* IObservable<T> | Observable<T> | Promise<T> */>): Observable<Array<T>>
    fun <T> forkJoin(vararg args: IObservable<T>): Observable<Array<T>>
    fun <T> forkJoin(vararg args: Observable<T>): Observable<Array<T>>
    fun <T> forkJoin(vararg args: Promise<T>): Observable<Array<T>>
    fun <TR> `when`(plan: Plan<TR>): Observable<TR>
    fun interval(period: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Number>
    fun timer(dueTime: Number, period: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Number>
    fun timer(dueTime: Number, scheduler: IScheduler? = definedExternally /* null */): Observable<Number>
    fun <TState, TResult> generateWithAbsoluteTime(initialState: TState, condition: (state: TState) -> Boolean, iterate: (state: TState) -> TState, resultSelector: (state: TState) -> TResult, timeSelector: (state: TState) -> Date, scheduler: IScheduler? = definedExternally /* null */): Observable<TResult>
    fun <TState, TResult> generateWithRelativeTime(initialState: TState, condition: (state: TState) -> Boolean, iterate: (state: TState) -> TState, resultSelector: (state: TState) -> TResult, timeSelector: (state: TState) -> Number, scheduler: IScheduler? = definedExternally /* null */): Observable<TResult>
    fun <T> repeat(value: T): Observable<T>
    fun <T> case(selector: () -> String, sources: `T$5`): Observable<T>
    fun <T> case(selector: () -> Number, sources: `T$6`): Observable<T>
}

external class CompositeDisposable(vararg disposables: IDisposable = definedExternally) : Disposable {
    override fun dispose() = definedExternally

    fun add(item: IDisposable)
    fun remove(item: IDisposable)

    companion object : CompositeDisposableStatic by definedExternally
}

external interface CompositeDisposableStatic
external interface SingleAssignmentDisposable {
    fun dispose()
    var isDisposed: Boolean
    fun getDisposable(): IDisposable
    fun setDisposable(value: IDisposable)

    companion object : SingleAssignmentDisposableStatic by definedExternally {
    }
}

external interface SingleAssignmentDisposableStatic
external interface SerialDisposable {
    fun dispose()
    var isDisposed: Boolean
    fun getDisposable(): IDisposable
    fun setDisposable(value: IDisposable)

    companion object : SerialDisposableStatic by definedExternally {
    }
}

external interface SerialDisposableStatic
external interface RefCountDisposable : Disposable {
    override fun dispose()
    override var isDisposed: Boolean?
    fun getDisposable(): IDisposable

    companion object : RefCountDisposableStatic by definedExternally {
    }
}

external interface RefCountDisposableStatic
external interface IScheduler {
    fun now(): Number
    fun <TState> schedule(state: TState, action: (scheduler: IScheduler, state: TState) -> IDisposable): IDisposable
    fun <TState> scheduleFuture(state: TState, dueTime: Number, action: (scheduler: IScheduler, state: TState) -> IDisposable): IDisposable
    fun <TState> scheduleFuture(state: TState, dueTime: Date, action: (scheduler: IScheduler, state: TState) -> IDisposable): IDisposable
    fun <TState> scheduleRecursive(state: TState, action: (state: TState, action: (state: TState) -> Unit) -> Unit): IDisposable
    //    fun <TState, TTime : dynamic /* Number | Date */> scheduleRecursiveFuture(state: TState, dueTime: TTime, action: (state: TState, action: (state: TState, dueTime: TTime) -> Unit) -> Unit): IDisposable
    fun <TState> schedulePeriodic(state: TState, period: Number, action: (state: TState) -> TState): IDisposable

    fun catch(handler: Function<*>): IScheduler
}

external interface SchedulerStatic {
    fun now(): Number
    fun normalize(timeSpan: Number): Number
    fun isScheduler(s: Any): Boolean
    var immediate: IScheduler
    var currentThread: ICurrentThreadScheduler
    var default: IScheduler
    var async: IScheduler
}

external var Scheduler: SchedulerStatic = definedExternally

external interface ICurrentThreadScheduler : IScheduler {
    fun scheduleRequired(): Boolean
}

external interface IObserver<T> {
    fun onNext(value: T)
    fun onError(exception: Any)
    fun onCompleted()
}

external interface Observer<T> : IObserver<T> {
    companion object : ObserverStatic by definedExternally {
    }

    fun makeSafe(disposable: IDisposable): Observer<T>
}

external interface ObserverStatic {
    fun <T> create(onNext: ((value: T) -> Unit)? = definedExternally /* null */, onError: ((exception: Any) -> Unit)? = definedExternally /* null */, onCompleted: (() -> Unit)? = definedExternally /* null */): Observer<T>
}

external interface Notification<T> {
    fun accept(observer: IObserver<T>)
    fun <TResult> accept(onNext: (value: T) -> TResult, onError: (exception: Any) -> TResult, onCompleted: () -> TResult): TResult
    fun toObservable(scheduler: IScheduler? = definedExternally /* null */): Observable<T>
    var hasValue: Boolean
    fun equals(other: Notification<T>): Boolean
    var kind: String
    var value: T
    var error: Any

    companion object : NotificationStatic by definedExternally {
    }
}

external interface NotificationStatic {
    fun <T> createOnNext(value: T): Notification<T>
    fun <T> createOnError(exception: Any): Notification<T>
    fun <T> createOnCompleted(): Notification<T>
}

external interface AnonymousObserver<T> : Observer<T> {
    override fun onNext(value: T)
    override fun onError(exception: Any)
    override fun onCompleted()

    companion object : AnonymousObserverStatic by definedExternally {
    }
}

external interface AnonymousObserverStatic
external interface CheckedObserver<T> : Observer<T> {
    fun checkAccess()
}

external interface `T$1`<T> {
    var length: Number
    @nativeGetter
    operator fun get(index: Number): T?

    @nativeSetter
    operator fun set(index: Number, value: T)
}

external interface ArrayObserveChange<T> {
    var type: String
    var `object`: Array<T>
    var name: String? get() = definedExternally; set(value) = definedExternally
    var oldValue: T? get() = definedExternally; set(value) = definedExternally
    var index: Number? get() = definedExternally; set(value) = definedExternally
    var removed: Array<T>? get() = definedExternally; set(value) = definedExternally
    var added: Number? get() = definedExternally; set(value) = definedExternally
}

external interface ObjectObserveChange<T> {
    var type: String
    var `object`: T
    var name: String
    var oldValue: Any? get() = definedExternally; set(value) = definedExternally
}

external interface `T$2`<T> {
    @nativeGetter
    operator fun get(key: String): T?

    @nativeSetter
    operator fun set(key: String, value: T)
}

external interface `T$3`<T> {
    @nativeGetter
    operator fun get(key: Number): T?

    @nativeSetter
    operator fun set(key: Number, value: T)
}

external interface `T$4` {
    var on: (name: String, cb: (e: Any) -> Any) -> Unit
    var off: (name: String, cb: (e: Any) -> Any) -> Unit
}

external interface PausableObservable<T> : Observable<T> {
    fun pause()
    fun resume()
}

external interface ControlledObservable<T> : Observable<T> {
    fun request(numberOfItems: Number? = definedExternally /* null */): IDisposable
    fun stopAndWait(): Observable<T>
    fun windowed(windowSize: Number): Observable<T>
}

external interface ISubject<T> : IObservable<T>, IObserver<T>, IDisposable {
    fun hasObservers(): Boolean
}

open external class Subject<T> : Observable<T>, Observer<T>, IDisposable {
    override fun dispose(): Unit = definedExternally

    override fun onNext(value: T): Unit = definedExternally

    override fun onError(exception: Any): Unit  = definedExternally

    override fun onCompleted(): Unit  = definedExternally

    override fun makeSafe(disposable: IDisposable): Observer<T> = definedExternally

    fun hasObservers(): Boolean = definedExternally
    var isDisposed: Boolean = definedExternally
    companion object : SubjectStatic by definedExternally
}

external interface SubjectStatic {
    fun <T> create(observer: IObserver<T>? = definedExternally /* null */, observable: IObservable<T>? = definedExternally /* null */): Subject<T>
}

external interface ConnectableObservable<T> : Observable<T> {
    fun connect(): IDisposable
    fun refCount(): Observable<T>
}

external interface `T$5` {
    @nativeGetter
    operator fun get(key: String): dynamic /* IObservable<T> | Observable<T> | Promise<T> */

    @nativeSetter
    operator fun <T> set(key: String, value: IObservable<T>)

    @nativeSetter
    operator fun <T> set(key: String, value: Observable<T>)

    @nativeSetter
    operator fun <T> set(key: String, value: Promise<T>)
}

external interface `T$6` {
    @nativeGetter
    operator fun get(key: Number): dynamic /* IObservable<T> | Observable<T> | Promise<T> */

    @nativeSetter
    operator fun <T> set(key: Number, value: IObservable<T>)

    @nativeSetter
    operator fun <T> set(key: Number, value: Observable<T>)

    @nativeSetter
    operator fun <T> set(key: Number, value: Promise<T>)
}

external open class Plan<T>
external interface Pattern2<T1, T2> {
    fun <T3> and(other: Observable<T3>): Pattern3<T1, T2, T3>
    fun <TR> thenDo(selector: (item1: T1, item2: T2) -> TR): Plan<TR>
}

external interface Pattern3<T1, T2, T3> {
    fun <T4> and(other: Observable<T4>): Pattern4<T1, T2, T3, T4>
    fun <TR> thenDo(selector: (item1: T1, item2: T2, item3: T3) -> TR): Plan<TR>
}

external interface Pattern4<T1, T2, T3, T4> {
    fun <T5> and(other: Observable<T5>): Pattern5<T1, T2, T3, T4, T5>
    fun <TR> thenDo(selector: (item1: T1, item2: T2, item3: T3, item4: T4) -> TR): Plan<TR>
}

external interface Pattern5<T1, T2, T3, T4, T5> {
    fun <T6> and(other: Observable<T6>): Pattern6<T1, T2, T3, T4, T5, T6>
    fun <TR> thenDo(selector: (item1: T1, item2: T2, item3: T3, item4: T4, item5: T5) -> TR): Plan<TR>
}

external interface Pattern6<T1, T2, T3, T4, T5, T6> {
    fun <T7> and(other: Observable<T7>): Pattern7<T1, T2, T3, T4, T5, T6, T7>
    fun <TR> thenDo(selector: (item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6) -> TR): Plan<TR>
}

external interface Pattern7<T1, T2, T3, T4, T5, T6, T7> {
    fun <T8> and(other: Observable<T8>): Pattern8<T1, T2, T3, T4, T5, T6, T7, T8>
    fun <TR> thenDo(selector: (item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7) -> TR): Plan<TR>
}

external interface Pattern8<T1, T2, T3, T4, T5, T6, T7, T8> {
    fun <T9> and(other: Observable<T9>): Pattern9<T1, T2, T3, T4, T5, T6, T7, T8, T9>
    fun <TR> thenDo(selector: (item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8) -> TR): Plan<TR>
}

external interface Pattern9<T1, T2, T3, T4, T5, T6, T7, T8, T9> {
    fun <TR> thenDo(selector: (item1: T1, item2: T2, item3: T3, item4: T4, item5: T5, item6: T6, item7: T7, item8: T8, item9: T9) -> TR): Plan<TR>
}

external interface TimeInterval<T> {
    var value: T
    var interval: Number
}

external interface Timestamp<T> {
    var value: T
    var timestamp: Number
}

external interface VirtualTimeScheduler<TAbsolute, TRelative> : IScheduler {
    fun add(from: TAbsolute, by: TRelative): TAbsolute
    fun toAbsoluteTime(duetime: TAbsolute): Number
    fun toRelativeTime(duetime: Number): TRelative
    fun start(): IDisposable
    fun stop()
    fun advanceTo(time: TAbsolute)
    fun advanceBy(time: TRelative)
    fun sleep(time: TRelative)
    var isEnabled: Boolean
//    fun getNext(): Rx.internals.ScheduledItem<TAbsolute>
}

external interface HistoricalScheduler : VirtualTimeScheduler<Number, Number> {
    companion object {
    }
}

external interface Subscription {
    fun equals(other: Subscription): Boolean
    override fun toString(): String

    companion object : SubscriptionStatic by definedExternally {
    }
}

external interface SubscriptionStatic
external interface Recorded {
    fun equals(other: Recorded): Boolean
    override fun toString(): String
    var time: Number
    var value: Any

    companion object : RecordedStatic by definedExternally {
    }
}

external interface RecordedStatic
external object ReactiveTest {
    var created: Number = definedExternally
    var subscribed: Number = definedExternally
    var disposed: Number = definedExternally
    fun onNext(ticks: Number, value: Any): Recorded = definedExternally
    fun onNext(ticks: Number, predicate: (value: Any) -> Boolean): Recorded = definedExternally
    fun onError(ticks: Number, exception: Any): Recorded = definedExternally
    fun onError(ticks: Number, predicate: (exception: Any) -> Boolean): Recorded = definedExternally
    fun onCompleted(ticks: Number): Recorded = definedExternally
    fun subscribe(subscribeAt: Number, unsubscribeAt: Number? = definedExternally /* null */): Subscription = definedExternally
}

external interface MockObserver<T> : Observer<T> {
    var messages: Array<Recorded>

    companion object : MockObserverStatic by definedExternally {
    }
}

external interface MockObserverStatic : ObserverStatic
external interface TestScheduler : VirtualTimeScheduler<Number, Number> {
    fun <T> createColdObservable(vararg records: Recorded): Observable<T>
    fun <T> createHotObservable(vararg records: Recorded): Observable<T>
    fun <T> createObserver(): MockObserver<T>
    fun <T> createResolvedPromise(ticks: Number, value: T): Promise<T>
    fun <T> createRejectedPromise(ticks: Number, value: T): Promise<T>
    fun <T> startWithTiming(create: () -> Observable<T>, createdAt: Number, subscribedAt: Number, disposedAt: Number): MockObserver<T>
    fun <T> startWithDispose(create: () -> Observable<T>, disposedAt: Number): MockObserver<T>
    fun <T> startWithCreate(create: () -> Observable<T>): MockObserver<T>

    companion object {
    }
}

external interface AnonymousObservable<T> : Observable<T>
external interface GroupedObservable<TKey, TElement> : Observable<TElement> {
    var key: TKey
    var underlyingObservable: Observable<TElement>
}

external interface AsyncSubject<T> : Subject<T> {
    companion object : AsyncSubjectStatic by definedExternally {
    }
}

external interface AsyncSubjectStatic
external class BehaviorSubject<T>(defaultValue: T = definedExternally) : Subject<T> {
    fun getValue(): T

    companion object : BehaviorSubjectStatic by definedExternally
}

external interface BehaviorSubjectStatic
external class ReplaySubject<T>(replayCount: Int = definedExternally) : Subject<T> {
    companion object : ReplaySubjectStatic by definedExternally
}

external interface ReplaySubjectStatic
external interface AnonymousSubject<T> : Subject<T> {
    companion object : AnonymousSubjectStatic by definedExternally
}

external interface AnonymousSubjectStatic
external interface Pauser {
    fun pause()
    fun resume()
}
