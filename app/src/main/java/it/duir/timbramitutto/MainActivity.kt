package it.duir.timbramitutto

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import eu.giovannidefrancesco.easysharedprefslib.SharedPreferenceStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),
                     MainView {

  private val presenter: MainPresenter by lazy {
    MainActivityPresenter(this, SharedPreferenceStorage(this, this.javaClass.name), Locale.getDefault())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    main_timer_btn.setOnClickListener({ presenter.toggleTimer() })
  }

  override fun onResume() {
    super.onResume()
    presenter.viewResumed()
  }

  override fun showStartText(time: String) {
    main_begin_timer.text = time
  }

  override fun showPlayButton() {
    main_timer_btn.setImageResource(R.drawable.ic_play_arrow)
    val color = ContextCompat.getColor(this, R.color.emerald)
    main_timer_btn.backgroundTintList = ColorStateList.valueOf(color)
  }

  override fun showEndText(time: String) {
    main_end_timer.text = time
  }

  override fun showStopButton() {
    val color = ContextCompat.getColor(this, R.color.red_orange)
    main_timer_btn.setImageResource(R.drawable.ic_stop)
    main_timer_btn.backgroundTintList = ColorStateList.valueOf(color)
  }

  override fun resetEndTime() {
    main_end_timer.text = getString(R.string.base_timer)
  }

  override fun showElapsedTime(elapsedTime: String) {
    main_time_elapsed.text = elapsedTime
  }

  override fun resetElapsedTime() {
    main_time_elapsed.text = getString(R.string.base_timer)
  }

}
