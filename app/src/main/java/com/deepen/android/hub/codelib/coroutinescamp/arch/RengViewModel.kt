package com.deepen.android.hub.codelib.coroutinescamp.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.deepen.android.hub.codelib.coroutinescamp.Api
import com.deepen.android.hub.codelib.coroutinescamp.model.Repo
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RengViewModel : ViewModel() {
  init {
    viewModelScope.launch {

    }
  }

  val repos = liveData {
    emit(loadUsers())
  }

  private suspend fun loadUsers(): List<Repo> {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.github.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .build()
    val api = retrofit.create(Api::class.java)
    return api.listReposKt("rengwuxian")
  }
}