package it.duir.timbramitutto.timer

import android.arch.lifecycle.ViewModel
import it.duir.timbramitutto.model.PunchcardDao

class PunchcardViewModel(private val punchcardDao: PunchcardDao) : ViewModel() {
  fun getPunchcard(id: Long?) = if (id != null) {
    punchcardDao.getOne(id)
  } else {
    punchcardDao.getOne(-1)
  }
}
