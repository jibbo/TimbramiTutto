package it.duir.timbramitutto.history

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.duir.timbramitutto.R
import it.duir.timbramitutto.model.AppDatabase
import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment(),
                        HistoryView,
                        SwipeListener,
                        TopOverScrollListener {

  private lateinit var historyViewModel: HistoryViewModel
  private lateinit var layoutManager: LinearLayoutManager
  private lateinit var adapter: HistoryAdapter
  private lateinit var presenter: HistoryPresenter

  private val swipeHelper = SwipeDeleteItemHelper(this)

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    context?.let {
      layoutManager = LinearLayoutOverscrollManager(context, LinearLayoutManager.VERTICAL, false, this)
      val punchcardDao = AppDatabase.getInstance(context).punchcardDao()
      presenter = HistoryFragmentPresenter(this, punchcardDao)
      historyViewModel = ViewModelProviders
          .of(this, ViewModelFactory(punchcardDao))
          .get(HistoryViewModel::class.java)
      historyViewModel.history.observe(this, presenter)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_history, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    history_list.layoutManager = layoutManager
    val dividerItemDecoration = DividerItemDecoration(view.context, layoutManager.orientation)
    ContextCompat.getDrawable(view.context, R.drawable.divider)?.let { dividerItemDecoration.setDrawable(it) }
    history_list.addItemDecoration(dividerItemDecoration)
    ItemTouchHelper(swipeHelper).attachToRecyclerView(history_list)
  }

  override fun showHistory(list: List<Punchcard>) {
    adapter = HistoryAdapter(list.toMutableList())
    history_list.adapter = adapter
    history_empty_msg.visibility = View.GONE
    history_list.visibility = View.VISIBLE
  }

  override fun showEmptyView() {
    history_list.visibility = View.VISIBLE
    history_empty_msg.visibility = View.VISIBLE
  }

  override fun removeItem(position: Int) {
    adapter.removeItem(position)
  }

  override fun onDelete(position: Int, punchcard: Punchcard) {
    presenter.punchcardRemoved(position, punchcard)
  }

  override fun showSearch() {
    history_search.visibility = View.VISIBLE
  }

  override fun hideSearch() {
    history_search.visibility = View.GONE
  }

  override fun onTopOverScroll() {
    presenter.topOverScroll()
  }

  override fun onTopOverScrollEnded() {
    presenter.topOverScrollEnded()
  }
}
