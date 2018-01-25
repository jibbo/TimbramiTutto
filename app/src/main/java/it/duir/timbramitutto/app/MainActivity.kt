package it.duir.timbramitutto.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import it.duir.timbramitutto.R
import it.duir.timbramitutto.timer.TimerFragment
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    main_bottom_navigation.setOnNavigationItemSelectedListener { item ->
      val transaction = supportFragmentManager.beginTransaction()
      val toShow = when (item.itemId) {
        R.id.action_timer -> TimerFragment()
        else -> Fragment()
      }
      transaction.replace(R.id.main_fragment_container, toShow, "PUNCH")
      transaction.commit()
      true
    }

    main_bottom_navigation.selectedItemId = R.id.action_timer
  }

}
