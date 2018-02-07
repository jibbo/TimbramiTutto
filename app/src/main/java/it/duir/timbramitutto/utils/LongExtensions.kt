package it.duir.timbramitutto.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toFormattedDate(pattern: String): String {
  val locale = Locale.getDefault()
  val dateFormatter = SimpleDateFormat(pattern, locale)
  val calendar = Calendar.getInstance(locale)
  calendar.timeInMillis = this
  return dateFormatter.format(calendar.time)
}

fun Long.toFormattedTime(pattern: String): String {
  val locale = Locale.getDefault()
  val dateFormatter = SimpleDateFormat(pattern, locale)
  val calendar = Calendar.getInstance(locale)
  calendar.timeInMillis = this
  return dateFormatter.format(calendar.time)
}

fun Long.toElapsedTimeString(): String {
  var time = this / 1000
  val seconds = time % 60
  time /= 60
  val minutes = time % 60
  time /= 60
  val hours = time % 24
  time /= 24
  val days = time
  var out = ""
  if (days > 0) {
    out += "${days}g "
  }
  if (hours > 0) {
    out += "${hours}o "
  }
  if (minutes > 0) {
    out += "${minutes}m "
  }
  if (seconds > 0) {
    out += "${seconds}s"
  } else if (hours < 1 && minutes < 1) {
    out += "1s"
  }
  return out
}
