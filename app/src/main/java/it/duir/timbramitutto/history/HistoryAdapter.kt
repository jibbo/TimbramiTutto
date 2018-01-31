package it.duir.timbramitutto.history

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.duir.timbramitutto.R
import it.duir.timbramitutto.model.Punchcard
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val history: List<Punchcard>) :
    RecyclerView.Adapter<HistoryAdapter.Companion.HistoryHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HistoryHolder {
    val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_history, parent, false)
    return HistoryHolder(view)
  }

  override fun onBindViewHolder(holder: HistoryHolder?, position: Int) {
    holder?.bindPunchCard(history[position])
  }

  override fun getItemCount(): Int {
    return history.size
  }

  companion object {
    class HistoryHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
      fun bindPunchCard(punchcard: Punchcard) {
        itemView.item_begin_time.text = punchcard.begin
        itemView.item_end_time.text = punchcard.end
        itemView.item_elapsed_time.text = punchcard.elapsed
      }
    }
  }

}


