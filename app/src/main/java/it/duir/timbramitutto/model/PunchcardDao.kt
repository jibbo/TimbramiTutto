package it.duir.timbramitutto.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface PunchcardDao {

  @Query("SELECT * FROM Punchcard WHERE id=(:punchcardId)")
  fun getOne(punchcardId: Long): LiveData<Punchcard>

  @Query("SELECT * FROM Punchcard ORDER BY begin DESC ")
  fun getAll(): LiveData<List<Punchcard>>

  @Query("SELECT * FROM Punchcard WHERE dateString LIKE :date")
  fun find(date: String): LiveData<List<Punchcard>>

  @Insert
  fun insert(punchcard: Punchcard): Long

  @Insert
  fun insertAll(vararg punchcards: Punchcard): Array<Long>

  @Update
  fun update(punchcard: Punchcard)

  @Delete
  fun delete(vararg punchcards: Punchcard)

}
