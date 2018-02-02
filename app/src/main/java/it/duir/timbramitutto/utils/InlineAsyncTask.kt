package it.duir.timbramitutto.utils

import android.os.AsyncTask

fun <R> async(body: () -> R) = InlineAsyncTask<Void, R>(body).execute()

class InlineAsyncTask<T, R>(private val backgroundFun: () -> R) : AsyncTask<T, Void, R>() {
  override fun doInBackground(vararg params: T) = backgroundFun.invoke()
}
