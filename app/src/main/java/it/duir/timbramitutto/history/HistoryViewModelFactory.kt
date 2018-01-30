package it.duir.timbramitutto.history



import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import it.duir.timbramitutto.app.Application

class HistoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory{

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    val punchCardDao = (context.applicationContext as Application).db.punchcardDao()
    return HistoryViewModel(punchCardDao) as T
  }
}