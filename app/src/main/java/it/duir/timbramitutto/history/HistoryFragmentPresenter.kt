package it.duir.timbramitutto.history

import it.duir.timbramitutto.model.Punchcard

class HistoryFragmentPresenter(private val view: HistoryView) : HistoryPresenter {
  override fun onChanged(list: List<Punchcard>?) {
    if (list == null || list.isEmpty()) {
      view.showEmptyView()
    } else {
      view.showHistory(list)
    }
  }
}
