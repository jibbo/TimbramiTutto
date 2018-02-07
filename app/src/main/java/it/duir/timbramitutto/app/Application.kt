package it.duir.timbramitutto.app

import android.app.Application

class Application : Application(){
  companion object {
    const val TIME_FORMAT = "HH:mm"
    const val DATE_FORMAT = "dd/MM/yy"
    const val BASE_TIME = 0L
  }
}
