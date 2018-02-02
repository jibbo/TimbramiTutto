package it.duir.timbramitutto.timer

import eu.giovannidefrancesco.easysharedprefslib.IStorage
import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.model.PunchcardDao
import it.duir.timbramitutto.utils.async
import it.duir.timbramitutto.utils.toElapsedTimeString
import it.duir.timbramitutto.utils.toFormattedTime
import java.util.*

class TimerFragmentPresenter(private val view: TimerView, private val storage: IStorage,
                             private val locale: Locale, private val punchcardDao: PunchcardDao) : TimerPresenter {

  companion object {
    val TIME_KEY = "timeInMillis"
    val TIME_FORMAT = "HH:mm"
    val DATE_FORMAT = "dd/MM/yy"
    val BASE_TIME = 0L
  }

  private var storedTime = BASE_TIME
  private var started = false

  override fun viewResumed() {
    storedTime = storage.get(TIME_KEY, BASE_TIME)
    if (storedTime != BASE_TIME) {
      updateView(storedTime.toFormattedTime())
      started = true
    }
  }

  override fun viewPaused() {
    started = false
  }

  override fun toggleTimer() {
    val time = getTime()
    savePunchcard(time)
    updateView(time.toFormattedTime())
    updateStorage(time)
    started = !started
  }

  private fun savePunchcard(time: Long) {
    if (started) {
      async {
        punchcardDao.insert(Punchcard(storedTime, time))
      }
    }
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

  private fun showElapsedTime() {
    val time = getTime() - storedTime
    view.showElapsedTime(time.toElapsedTimeString())
  }

  private fun getTime(): Long {
    return Calendar.getInstance(locale).timeInMillis
  }

}
