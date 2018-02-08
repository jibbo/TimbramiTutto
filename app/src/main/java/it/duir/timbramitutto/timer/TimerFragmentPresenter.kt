package it.duir.timbramitutto.timer

import android.annotation.SuppressLint
import android.support.annotation.VisibleForTesting
import eu.giovannidefrancesco.easysharedprefslib.IStorage
import it.duir.timbramitutto.app.Application.Companion.BASE_TIME
import it.duir.timbramitutto.app.Application.Companion.TIME_FORMAT
import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.model.PunchcardDao
import it.duir.timbramitutto.utils.asFormattedDate
import it.duir.timbramitutto.utils.toElapsedTimeString
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import java.util.*

class TimerFragmentPresenter(private val view: TimerView, private val storage: IStorage,
                             private val locale: Locale, private val punchcardDao: PunchcardDao) : TimerPresenter {

  companion object {
    const val TIME_KEY = "timeInMillis"
  }

  @VisibleForTesting
  var storedTime = BASE_TIME

  @VisibleForTesting
  var started = false

  @SuppressLint("VisibleForTests")
  override fun viewResumed() {
    storedTime = storage.get(TIME_KEY, BASE_TIME)
    if (storedTime != BASE_TIME) {
      updateView(storedTime.asFormattedDate(TIME_FORMAT))
      started = true
    }
  }

  override fun viewPaused() {
    started = false
  }

  @SuppressLint("VisibleForTests")
  override fun toggleTimer() {
    val time = getTime()
    savePunchcardIfNecessary(time)
    updateView(time.asFormattedDate(TIME_FORMAT))
    updateStorage(time)
    started = !started
  }

  @VisibleForTesting
  fun updateView(timeString: String) {
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

  @VisibleForTesting
  fun savePunchcardIfNecessary(time: Long): Job? {
    if (started) {
      return async { punchcardDao.insert(Punchcard(storedTime, time)) }
    }
    return null
  }

  @VisibleForTesting
  fun updateStorage(time: Long) {
    if (started) {
      storage.reset()
    } else {
      storedTime = time
      storage.store(TIME_KEY, getTime())
    }
  }

  @VisibleForTesting
  fun showElapsedTime() {
    val time = getTime() - storedTime
    view.showElapsedTime(time.toElapsedTimeString())
  }

  @VisibleForTesting
  fun getTime(): Long {
    return Calendar.getInstance(locale).timeInMillis
  }

}
