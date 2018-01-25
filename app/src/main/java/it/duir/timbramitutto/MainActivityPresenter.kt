package it.duir.timbramitutto

import eu.giovannidefrancesco.easysharedprefslib.IStorage
import java.text.SimpleDateFormat
import java.util.*

class MainActivityPresenter(private val view: MainView, private val storage: IStorage,
                            private val locale: Locale) : MainPresenter {

  companion object {
    val TIME_KEY = "timeInMillis"
    val TIME_FORMAT = "HH:mm"
    val BASE_TIME = 0L
  }

  private val dateFormatter = SimpleDateFormat(TIME_FORMAT, locale)

  private var storedTime = BASE_TIME
  private var started = false

  override fun viewResumed() {
    storedTime = storage.get(TIME_KEY, BASE_TIME)
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

  private fun getElapsedTimeString(time: Long): String {
    val elapsedTime = time / 1000
    val minutes = elapsedTime % 3600 / 60
    val hours = elapsedTime / 3600
    return String.format("%d:%d", hours, minutes)
  }

  private fun getTime(): Long {
    return Calendar.getInstance(locale).timeInMillis
  }

}