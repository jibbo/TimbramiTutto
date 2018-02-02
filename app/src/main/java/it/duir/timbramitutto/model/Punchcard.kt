package it.duir.timbramitutto.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Punchcard(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                     var begin: Long? = null,
                     var end: Long? = null,
                     var elapsed: Long? = null) {

  constructor(begin: Long, end: Long) : this(begin = begin, end = end, elapsed = end - begin)
}
