package it.duir.timbramitutto.timer

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.giovannidefrancesco.easysharedprefslib.SharedPreferenceStorage
import it.duir.timbramitutto.R
import it.duir.timbramitutto.model.AppDatabase
import it.duir.timbramitutto.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_punch.*
import java.util.*

class TimerFragment : Fragment(),
                      TimerView {

  private lateinit var presenter: TimerPresenter

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    context?.let {
      val punchcardDao = AppDatabase.getInstance(context).punchcardDao()
      presenter = TimerFragmentPresenter(
          this,
          SharedPreferenceStorage(context, this.javaClass.name),
          Locale.getDefault(),
          punchcardDao
      )
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_punch, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    punch_timer_btn.setOnClickListener({ presenter.toggleTimer() })
  }

  override fun onResume() {
    super.onResume()
    presenter.viewResumed()
  }

  override fun onPause() {
    super.onPause()
    presenter.viewPaused()
  }

  override fun showStartText(time: String) {
    punch_begin_timer.text = time
  }

  override fun showPlayButton() {
    punch_timer_btn.setImageResource(R.drawable.ic_play_arrow)
    val color = context?.let { ContextCompat.getColor(it, R.color.emerald) }
    punch_timer_btn.backgroundTintList = color?.let { ColorStateList.valueOf(it) }
  }

  override fun showEndText(time: String) {
    punch_end_timer.text = time
  }

  override fun showStopButton() {
    val color = context?.let { ContextCompat.getColor(it, R.color.red_orange) }
    punch_timer_btn.setImageResource(R.drawable.ic_stop)
    punch_timer_btn.backgroundTintList = color?.let { ColorStateList.valueOf(it) }
  }

  override fun resetEndTime() {
    punch_end_timer.text = getString(R.string.base_timer)
  }

  override fun showElapsedTime(elapsedTime: String) {
    punch_time_elapsed.text = elapsedTime
  }

  override fun resetElapsedTime() {
    punch_time_elapsed.text = getString(R.string.base_timer)
  }

}
