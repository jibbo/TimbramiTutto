package it.duir.timbramitutto.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDateTime(pattern: String): Long {
  val locale = Locale.getDefault()
  val dateFormatter = SimpleDateFormat(pattern, locale)
  return try {
    dateFormatter.parse(this).time
  } catch (ex: ParseException) {
    0
  }
}
