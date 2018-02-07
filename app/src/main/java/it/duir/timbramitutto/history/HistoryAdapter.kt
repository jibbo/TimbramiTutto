package it.duir.timbramitutto.history

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.duir.timbramitutto.R
import it.duir.timbramitutto.app.Application.Companion.DATE_FORMAT
import it.duir.timbramitutto.app.Application.Companion.TIME_FORMAT
import it.duir.timbramitutto.model.Punchcard
import it.duir.timbramitutto.utils.toElapsedTimeString
import it.duir.timbramitutto.utils.toFormattedDate
import it.duir.timbramitutto.utils.toFormattedTime
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val history: MutableList<Punchcard>) :
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

  fun removeItem(position: Int) {
    history.removeAt(position)
    notifyItemRemoved(position)
  }

  companion object {
    class HistoryHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
      lateinit var punchard: Punchcard
      fun bindPunchCard(card: Punchcard) {
        punchard = card
        val dateCaption = itemView.context.resources.getString(
            R.string.date_caption,
            card.begin?.toFormattedDate(DATE_FORMAT),
            card.begin?.toFormattedTime(TIME_FORMAT),
            card.end?.toFormattedTime(TIME_FORMAT)
        )
        itemView.item_date.text = dateCaption
        itemView.item_elapsed_time.text = card.elapsed?.toElapsedTimeString()
      }
    }
  }

}


