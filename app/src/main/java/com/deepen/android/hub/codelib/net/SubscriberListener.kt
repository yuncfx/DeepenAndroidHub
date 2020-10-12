package com.deepen.android.hub.codelib.net

interface SubscriberListener<T> {
    fun onError(e: Throwable?)
    fun onNext(t: T)
    abstract class SimpleSubscriberListener<T> : SubscriberListener<T> {
        override fun onError(e: Throwable?) {}
        abstract override fun onNext(t: T)
    }
}