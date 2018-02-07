package it.duir.timbramitutto.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import it.duir.timbramitutto.app.Application.Companion.DATE_FORMAT
import it.duir.timbramitutto.model.Punchcard.Companion.TABLE_NAME
import it.duir.timbramitutto.utils.asFormattedDate

@Entity(tableName = TABLE_NAME)
data class Punchcard(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                     var begin: Long? = null,
                     var end: Long? = null,
                     var elapsed: Long? = null) {

  var dateString: String? = ""

  constructor(begin: Long, end: Long) : this(begin = begin, end = end, elapsed = end - begin)

  init {
    dateString = begin?.asFormattedDate(DATE_FORMAT)
  }

  companion object {
    const val TABLE_NAME = "Punchcard"
  }

}
