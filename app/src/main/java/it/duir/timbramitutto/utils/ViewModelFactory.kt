package it.duir.timbramitutto.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import it.duir.timbramitutto.history.HistoryViewModel
import it.duir.timbramitutto.model.PunchcardDao

class ViewModelFactory(private val punchcardDao: PunchcardDao) : ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return HistoryViewModel(punchcardDao) as T
  }
}
