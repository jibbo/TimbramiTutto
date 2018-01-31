package it.duir.timbramitutto.history

import it.duir.timbramitutto.model.Punchcard

interface HistoryView {
  fun showHistory(list: List<Punchcard>)
  fun showEmptyView()
}
