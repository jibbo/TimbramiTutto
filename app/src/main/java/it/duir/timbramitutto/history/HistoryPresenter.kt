package it.duir.timbramitutto.history

import android.arch.lifecycle.Observer
import it.duir.timbramitutto.model.Punchcard

interface HistoryPresenter : Observer<List<Punchcard>> {
  fun punchcardRemoved(position: Int, punchcard: Punchcard)
}
