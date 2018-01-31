package it.duir.timbramitutto.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Punchcard::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

  abstract fun punchcardDao(): PunchcardDao

  companion object {
    private val DB_NAME = "punchcard_history"
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase = INSTANCE ?: buildDb(context).also { INSTANCE = it }

    private fun buildDb(context: Context): AppDatabase =
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
  }

}
