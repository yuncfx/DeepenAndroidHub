package com.deepen.android.hub.coroutinescamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.deepen.android.hub.R
import com.deepen.android.hub.coroutinescamp.model.Repo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.coruntines_activity_main.*
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class CoroutinesMainActivity : AppCompatActivity() {
  val disposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.coruntines_activity_main)

    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.github.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
      .build()

    val api = retrofit.create(Api::class.java)

    /*api.listRepos("rengwuxian")
      .enqueue(object : Callback<List<Repo>?> {
        override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {

        }

        override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {
          textView.text = response.body()?.get(0)?.name
          api.listRepos("google")
            .enqueue(object : Callback<List<Repo>?> {
              override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {

              }

              override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {

              }
            })
        }
      })*/

    lifecycleScope.launch {
      try {
        val repos: List<Repo> = api.listReposKt("rengwuxian")
        textView.text = repos[0].name + "-kt"
      } catch (e: Exception) {
        textView.text = e.message
      }
    }

    Single.zip<List<Repo>, List<Repo>, String>(
      api.listReposRx("rengwuxian"),
      api.listReposRx("google"),
      BiFunction {repos1, repos2 -> "${repos1[0].name} - ${repos2[0].name}" }
    ).observeOn(AndroidSchedulers.mainThread())
      .subscribe(object : SingleObserver<String> {
        override fun onSuccess(combined: String) {
          textView.text = combined
        }

        override fun onSubscribe(d: Disposable) {
          disposable.add(d)
        }

        override fun onError(e: Throwable) {
          textView.text = e.message
        }
      })

    /*GlobalScope.launch(Dispatchers.Main) {
      val rengwuxian = async { api.listReposKt("rengwuxian") }
      val google = async { api.listReposKt("google") }
      textView.text = "${rengwuxian.await()[0].name} + ${google.await()[0].name}"
    }*/

    GlobalScope.launch {
      ioCode1()
      uiCode1()
    }
  }

  override fun onDestroy() {
    disposable.dispose()
    super.onDestroy()
  }

  private val executor = ThreadPoolExecutor(5, 20, 1, TimeUnit.MINUTES, LinkedBlockingDeque())

  private fun classicIoCode1(uiThread: Boolean = true, block: () -> Unit) {
    executor.execute {
      println("Coroutines Camp classic io1 ${Thread.currentThread().name}")
      if (uiThread) {
        runOnUiThread {
          block()
        }
      } else {
        block()
      }
    }
  }

  private suspend fun ioCode1() {
    withContext(Dispatchers.IO) {
      println("Coroutines Camp io1 ${Thread.currentThread().name}")
    }
  }

  private suspend fun ioCode2() {
    withContext(Dispatchers.IO) {
      println("Coroutines Camp io2 ${Thread.currentThread().name}")
    }
  }

  private suspend fun ioCode3() {
    withContext(Dispatchers.IO) {
      println("Coroutines Camp io3 ${Thread.currentThread().name}")
    }
  }

  private fun uiCode1() {
    println("Coroutines Camp ui1 ${Thread.currentThread().name}")
  }

  private fun uiCode2() {
    println("Coroutines Camp ui2 ${Thread.currentThread().name}")
  }

  private fun uiCode3() {
    println("Coroutines Camp ui3 ${Thread.currentThread().name}")
  }
}