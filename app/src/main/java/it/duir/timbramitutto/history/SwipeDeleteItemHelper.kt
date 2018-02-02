package it.duir.timbramitutto.history

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class SwipeDeleteItemHelper(private val listener: SwipeListener): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
  override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?,
                      target: RecyclerView.ViewHolder?): Boolean {
    // ignored because I don't want re-ordering now
    return false
  }

  override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
    if(viewHolder is HistoryAdapter.Companion.HistoryHolder){
      listener.onDelete(viewHolder.layoutPosition, viewHolder.punchard)
    }
  }

}
