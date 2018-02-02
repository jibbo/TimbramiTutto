package it.duir.timbramitutto.history

import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.model.PunchcardDao
import it.duir.timbramitutto.utils.async

class HistoryFragmentPresenter(private val view: HistoryView,
                               private val punchcardDao: PunchcardDao) : HistoryPresenter {

  override fun onChanged(list: List<Punchcard>?) {
    if (list == null || list.isEmpty()) {
      view.showEmptyView()
    } else {
      view.showHistory(list)
    }
  }

  override fun punchcardRemoved(position: Int, punchcard: Punchcard) {
    async { punchcardDao.delete(punchcard) }
    view.removeItem(position)
  }

}
