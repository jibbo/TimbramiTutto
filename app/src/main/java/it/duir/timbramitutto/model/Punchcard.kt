package it.duir.timbramitutto.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Punchcard(@PrimaryKey val id: Long, val begin: String, val end: String, val elapsed: String)
