package it.duir.timbramitutto.utils

import kotlinx.coroutines.experimental.launch

fun <T> async(body: suspend () -> T) = launch { body.invoke() }
