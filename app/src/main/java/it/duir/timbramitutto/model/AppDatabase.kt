package it.duir.timbramitutto.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Punchcard::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

  abstract fun punchcardDao(): PunchcardDao
}
