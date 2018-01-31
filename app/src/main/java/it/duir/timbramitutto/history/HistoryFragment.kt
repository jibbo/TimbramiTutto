package it.duir.timbramitutto.history

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.duir.timbramitutto.R
import it.duir.timbramitutto.model.AppDatabase
import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment(),
                        HistoryView {

  private val presenter: HistoryPresenter by lazy { HistoryFragmentPresenter(this) }

  private lateinit var historyViewModel: HistoryViewModel
  private lateinit var layoutManager: LinearLayoutManager
  private lateinit var adapter: HistoryAdapter

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    context?.let {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      val punchcardDao = AppDatabase.getInstance(context).punchcardDao()
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
  }

  override fun showHistory(list: List<Punchcard>) {
    adapter = HistoryAdapter(list)
    history_list.adapter = adapter
    history_empty_msg.visibility = View.GONE
    history_list.visibility = View.VISIBLE
  }

  override fun showEmptyView() {
    history_list.visibility = View.VISIBLE
    history_empty_msg.visibility = View.VISIBLE
  }
}
