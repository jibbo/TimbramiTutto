package it.duir.timbramitutto.timer

interface TimerView {
  fun showPlayButton()
  fun showEndText(time: String)
  fun showStopButton()
  fun showStartText(time: String)
  fun resetEndTime()
  fun showElapsedTime(elapsedTime: String)
  fun resetElapsedTime()
}
