package it.duir.timbramitutto

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.main_fragment_container, PunchFragment(), "PUNCH")
    transaction.commit()

  }

}
