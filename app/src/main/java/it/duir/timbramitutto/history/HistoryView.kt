package it.duir.timbramitutto.history

import it.duir.timbramitutto.model.Punchcard

interface HistoryView {
  fun showHistory(list: List<Punchcard>)
  fun showEmptyView()
  fun removeItem(position: Int)
  fun showSearch()
  fun hideSearch()
  fun updateSearchTerm(time: Long?)
}
