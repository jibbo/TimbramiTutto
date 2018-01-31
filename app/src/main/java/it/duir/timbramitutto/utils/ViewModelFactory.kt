package it.duir.timbramitutto.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import it.duir.timbramitutto.history.HistoryViewModel
import it.duir.timbramitutto.model.PunchcardDao
import it.duir.timbramitutto.timer.PunchcardViewModel

class ViewModelFactory(private val punchcardDao: PunchcardDao) : ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(modelClass.name == HistoryViewModel::class.java.name) {
      return HistoryViewModel(punchcardDao) as T
    }
    return PunchcardViewModel(punchcardDao) as T
  }
}
