package it.duir.timbramitutto.app

import android.app.Application
import android.arch.persistence.room.Room
import it.duir.timbramitutto.model.AppDatabase

class Application : Application() {
  companion object {
    val DB_NAME = "punchcard_history"
  }

  val db by lazy {
    Room.databaseBuilder(this, AppDatabase::class.java, DB_NAME).build()
  }

}