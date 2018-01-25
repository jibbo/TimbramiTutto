package it.duir.timbramitutto.model

import android.arch.persistence.room.Entity
import java.util.*

@Entity
data class PunchCard(val date: Date, val begin: String, val end: String, val elapsed: String)