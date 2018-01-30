package it.duir.timbramitutto.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PunchcardDao {

  @get:Query("SELECT * FROM Punchcard")
  val all: LiveData<List<Punchcard>>

  @Insert
  fun insert(vararg punchcards: Punchcard)

  @Delete
  fun delete(vararg punchcards: Punchcard)

}
