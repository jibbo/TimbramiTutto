package it.duir.timbramitutto.history

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.model.PunchcardDao

class HistoryViewModel(private val punchCardDao: PunchcardDao) : ViewModel() {
  fun history(time: Long?): LiveData<List<Punchcard>> = if (time != null) {
    punchCardDao.find(time)
  } else {
    punchCardDao.getAll()
  }
}
