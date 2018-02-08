package it.duir.timbramitutto.timer.timer.helpers

import org.mockito.ArgumentMatchers

fun <T> any(clazz: Class<T>) = clazz.getDeclaredConstructor().newInstance()
fun <T> eq(obj: T?): T {
  if (obj == null) {
    throw RuntimeException("It cannot be null")
  }
  return ArgumentMatchers.eq(obj)
}


