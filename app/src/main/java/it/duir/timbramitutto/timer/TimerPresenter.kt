package it.duir.timbramitutto.timer

import android.arch.lifecycle.Observer
import it.duir.timbramitutto.model.Punchcard

interface TimerPresenter : Observer<Punchcard> {
  fun viewResumed()
  fun viewPaused()
  fun toggleTimer()
}
