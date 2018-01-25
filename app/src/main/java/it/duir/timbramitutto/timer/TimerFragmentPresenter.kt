package it.duir.timbramitutto.timer

import eu.giovannidefrancesco.easysharedprefslib.IStorage
import java.text.SimpleDateFormat
import java.util.*

class TimerFragmentPresenter(private val view: TimerView, private val storage: IStorage,
                             private val locale: Locale) : TimerPresenter {

  companion object {
    val TIME_KEY = "timeInMillis"
    val TIME_FORMAT = "HH:mm"
    val BASE_TIME = 0L
  }

  private val dateFormatter = SimpleDateFormat(TIME_FORMAT, locale)

  private var storedTime = BASE_TIME
  private var started = false

  override fun viewResumed() {
    storedTime = storage.get(TIME_KEY,
                             BASE_TIME)
    if (storedTime != BASE_TIME) {
      updateView(getFormattedTime(storedTime))
      started = true
    }
  }

  override fun toggleTimer() {
    val time = getTime()
    updateView(getFormattedTime(time))
    updateStorage(time)
    started = !started
  }

  private fun updateStorage(time: Long) {
    if (started) {
      storage.reset()
    } else {
      storedTime = time
      storage.store(TIME_KEY, getTime())
    }
  }

  private fun updateView(timeString: String) {
    if (started) {
      view.showPlayButton()
      view.showEndText(timeString)
      showElapsedTime()
    } else {
      view.showStopButton()
      view.showStartText(timeString)
      view.resetEndTime()
      view.resetElapsedTime()
    }
  }

  private fun getFormattedTime(timeInMillis: Long): String {
    val calendar = Calendar.getInstance(locale)
    calendar.timeInMillis = timeInMillis
    return dateFormatter.format(calendar.time)
  }

  private fun showElapsedTime() {
    val time = getTime() - storedTime
    val elapsedTimeString = getElapsedTimeString(time)
    view.showElapsedTime(elapsedTimeString)
  }

  private fun getElapsedTimeString(elapsedTime: Long): String {
    var time = elapsedTime / 1000
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
    }
    return out
  }

  private fun getTime(): Long {
    return Calendar.getInstance(locale).timeInMillis
  }

}