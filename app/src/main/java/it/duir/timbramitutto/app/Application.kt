package it.duir.timbramitutto.app

import android.app.Application
import android.arch.persistence.room.Room
import it.duir.timbramitutto.model.AppDatabase

class Application : Application(){
  companion object {
    const val TIME_KEY = "timeInMillis"
    const val TIME_FORMAT = "HH:mm"
    const val DATE_FORMAT = "dd/MM/yy"
    const val BASE_TIME = 0L
  }
}
