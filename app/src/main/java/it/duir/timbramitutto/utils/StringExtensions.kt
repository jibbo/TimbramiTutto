package it.duir.timbramitutto.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.contains(what: String, times: Int): Boolean {
  var count = 0
  for (c in this) {
    if (c.toString() == what) {
      count++
    }
  }
  return count == times
}

fun String.toDateTime(pattern: String): Long {
  val locale = Locale.getDefault()
  val dateFormatter = SimpleDateFormat(pattern, locale)
  return try {
    dateFormatter.parse(this).time
  } catch (ex: ParseException) {
    0
  }
}
