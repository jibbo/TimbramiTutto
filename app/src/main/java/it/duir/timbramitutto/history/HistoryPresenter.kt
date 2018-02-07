package it.duir.timbramitutto.history

import android.arch.lifecycle.Observer
import it.duir.timbramitutto.model.Punchcard

interface HistoryPresenter : Observer<List<Punchcard>> {
  fun viewReady()
  fun punchcardRemoved(position: Int, punchcard: Punchcard)
  fun topOverScroll()
  fun topOverScrollEnded()
  fun searchTermChanged(searchTerm: String)
}
