package it.duir.timbramitutto.history

import it.duir.timbramitutto.model.Punchcard

interface SwipeListener {
  fun onDelete(position: Int, punchcard: Punchcard)
}
