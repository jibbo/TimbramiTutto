package it.duir.timbramitutto.history

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class LinearLayoutOverscrollManager(context: Context?, orientation: Int, reverseLayout: Boolean,
                                    private val listener: TopOverScrollListener)
  : LinearLayoutManager(context, orientation, reverseLayout) {

  override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
    val scrollRange = super.scrollVerticallyBy(dy, recycler, state)
    val overscroll = dy - scrollRange
    if (overscroll < 0) {
      listener.onTopOverScroll()
    }
    if(overscroll==0){
      listener.onTopOverScrollEnded()
    }
    return super.scrollVerticallyBy(dy, recycler, state)
  }
}
