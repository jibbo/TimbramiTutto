package it.duir.timbramitutto

interface MainView{
  fun showPlayButton()
  fun showEndText(time: String)
  fun showStopButton()
  fun showStartText(time: String)
  fun resetEndTime()
  fun showElapsedTime(elapsedTime: String)
  fun resetElapsedTime()
}