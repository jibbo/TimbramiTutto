package it.duir.timbramitutto.history

import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.model.PunchcardDao
import it.duir.timbramitutto.timer.TimerFragmentPresenter
import it.duir.timbramitutto.utils.async
import it.duir.timbramitutto.utils.toDateTime

class HistoryFragmentPresenter(private val view: HistoryView,
                               private val punchcardDao: PunchcardDao) : HistoryPresenter {

  override fun viewReady() {
    view.updateSearchTerm(null)
  }

  override fun punchcardRemoved(position: Int, punchcard: Punchcard) {
    async { punchcardDao.delete(punchcard) }
    view.removeItem(position)
  }

  override fun topOverScroll() {
    view.showSearch()
  }

  override fun topOverScrollEnded() {
    view.hideSearch()
  }

  override fun searchTermChanged(searchTerm: String) {
    if (isLookingForDate(searchTerm)) {
      val time = searchTerm.toDateTime(TimerFragmentPresenter.DATE_FORMAT)
      async { view.updateSearchTerm(time) }
    } else {
      async { view.updateSearchTerm(null) }
    }
  }

  override fun onChanged(list: List<Punchcard>?) {
    if (list == null || list.isEmpty()) {
      view.showEmptyView()
    } else {
      view.showHistory(list)
    }
  }

  private fun isLookingForDate(searchTerm: String): Boolean {
    val searchTermArr = searchTerm.split("/")
    return searchTermArr.size == 3 && searchTermArr[2].length == 2
  }

}
