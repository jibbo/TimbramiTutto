package it.duir.timbramitutto.history

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.model.PunchcardDao

class HistoryViewModel(private val punchCardDao: PunchcardDao) : ViewModel() {
  val history: LiveData<List<Punchcard>> by lazy { punchCardDao.all }
}
