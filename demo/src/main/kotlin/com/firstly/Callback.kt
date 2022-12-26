package com.firstly

interface Callback<T : Any> {
  fun onSuccess(data: T)
  fun onFail(throwable: Throwable)
}