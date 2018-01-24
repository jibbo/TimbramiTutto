package it.duir.timbramitutto

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private var started = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    main_timer_btn.setOnClickListener(toggleTimerButton())
  }

  private fun toggleTimerButton() = { view: View? ->
    val btn: FloatingActionButton = view as FloatingActionButton
    //    if (started) {
    //      main_end_timer.text = System.currentTimeMillis().toString()
    //    } else {
    //      main_begin_timer.text = System.currentTimeMillis().toString()
    //    }
    started = !started
    val color = if (started) {
      btn.setImageResource(R.drawable.ic_stop)
      ContextCompat.getColor(this, R.color.red_orange)
    } else {
      btn.setImageResource(R.drawable.ic_play_arrow)
      ContextCompat.getColor(this, R.color.emerald)
    }
    btn.backgroundTintList = ColorStateList.valueOf(color)
//    btn.rippleColor = color
  }
}
