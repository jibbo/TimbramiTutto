package it.duir.timbramitutto

import eu.giovannidefrancesco.easysharedprefslib.IStorage
import java.text.SimpleDateFormat
import java.util.*

class MainActivityPresenter(private val view: MainView, private val storage: IStorage, locale: Locale) : MainPresenter {
  companion object {
    val TIME_STRING_KEY = "timeString"
    val TIME_FORMAT = "HH:mm"
    val BASE_STRING = "--:--"
  }

  private val dateFormatter = SimpleDateFormat(TIME_FORMAT, locale)

  private val storedTime = storage.get(TIME_STRING_KEY, BASE_STRING)

  private var started: Boolean

  init {
    if (storedTime == BASE_STRING) {
      started = true
      updateView(storedTime)
    } else {
      started = false
    }
  }

  override fun toggleTimer() {
    updateView(getFormattedTime())
    started = !started
  }

  private fun updateView(timeString: String) {
    if (started) {
      view.showPlayButton()
      view.showEndText(timeString)
      storage.reset()
    } else {
      storage.store(TIME_STRING_KEY, timeString)
      view.showStopButton()
      view.showStartText(timeString)
      view.resetEndTime()
      view.resetElapsedTime()
    }
  }

  private fun getFormattedTime(): String {
    val calendar = Calendar.getInstance()
    return dateFormatter.format(calendar.time)
  }

}