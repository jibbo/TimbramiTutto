package it.duir.timbramitutto.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface PunchcardDao {

  @Query("SELECT * FROM Punchcard WHERE id=(:punchcardId)")
  fun getOne(punchcardId: Long): LiveData<Punchcard>

  @Query("SELECT * FROM Punchcard")
  fun getAll(): LiveData<List<Punchcard>>

  @Insert
  fun insert(punchcard: Punchcard): Long

  @Insert
  fun insertAll(vararg punchcards: Punchcard): Array<Long>

  @Update
  fun update(punchcard: Punchcard)

  @Delete
  fun delete(vararg punchcards: Punchcard)

}
