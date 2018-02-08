package it.duir.timbramitutto.timer.timer

import eu.giovannidefrancesco.easysharedprefslib.IStorage
import it.duir.timbramitutto.app.Application.Companion.BASE_TIME
import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.model.PunchcardDao
import it.duir.timbramitutto.timer.TimerFragmentPresenter
import it.duir.timbramitutto.timer.TimerFragmentPresenter.Companion.TIME_KEY
import it.duir.timbramitutto.timer.TimerView
import it.duir.timbramitutto.timer.timer.helpers.eq
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class TimerFragmentPresenterTest {

  @Mock private lateinit var storage: IStorage
  @Mock private lateinit var view: TimerView
  @Mock private lateinit var punchcardDao: PunchcardDao

  private lateinit var locale: Locale
  private lateinit var presenter: TimerFragmentPresenter

  @Before
  fun setUp() {
    locale = Locale.getDefault()
    presenter = TimerFragmentPresenter(view, storage, locale, punchcardDao)
  }

  @Test
  fun testViewResumed() {
    `when`(storage.get(TIME_KEY, BASE_TIME)).thenReturn(BASE_TIME)
    presenter.viewResumed()
    verify(storage).get(TIME_KEY, BASE_TIME)
    verify(view, never()).showStartText(anyString())
  }

  @Test
  fun testViewResumedAfterPause() {
    `when`(storage.get(TIME_KEY, BASE_TIME)).thenReturn(1L)
    presenter.viewPaused()
    presenter.viewResumed()
    verify(storage, times(1)).get(TIME_KEY, BASE_TIME)
    verify(view).showStartText(anyString())
  }

  @Test
  fun testViewResumedWithTimeStored() {
    val timeInMillis = Calendar.getInstance(locale).timeInMillis
    `when`(storage.get(TIME_KEY, BASE_TIME)).thenReturn(timeInMillis)
    presenter.viewResumed()
    verify(storage).get(TIME_KEY, BASE_TIME)
    verify(view).showStartText(anyString())
  }

  @Test
  fun testToggleButtonWhenNoStarted() {
    presenter.toggleTimer()
    verify(view).showStopButton()
    verify(view).showStartText(anyString())
    verify(view).resetEndTime()
    verify(view).resetElapsedTime()
  }

  @Test
  fun testToggleButtonWhenAlreadyStarted() {
    presenter.toggleTimer()
    presenter.toggleTimer()
    verify(view).showPlayButton()
    verify(view).showEndText(anyString())
    verify(view).showElapsedTime(anyString())
  }

  @Test
  fun testUpdateViewAlreadyStarted() {
    presenter.started = true

    presenter.updateView("a")
    verify(view).showPlayButton()
    verify(view).showEndText("a")
    verify(view).showElapsedTime(anyString())
  }

  @Test
  fun testUpdateViewNotStarted() {
    presenter.started = false

    presenter.updateView("")
    verify(view).showStopButton()
    verify(view).showStartText("")
    verify(view).resetEndTime()
    verify(view).resetElapsedTime()
  }

  @Test
  fun testSavePunchCard() = runBlocking<Unit> {
    presenter.started = true
    presenter.storedTime = 1L
    val time = 100L

    val savePunchcardIfNecessary = presenter.savePunchcardIfNecessary(time)
    savePunchcardIfNecessary?.join()
    verify(punchcardDao).insert(eq(Punchcard(presenter.storedTime, time)))
  }

  @Test
  fun testDoNotSavePunchCard() = runBlocking<Unit> {
    presenter.started = false
    presenter.storedTime = 1L
    val time = 100L

    val savePunchcardIfNecessary = presenter.savePunchcardIfNecessary(time)
    savePunchcardIfNecessary?.join()
    verify(punchcardDao, never()).insert(eq(Punchcard(presenter.storedTime, time)))
  }
}

