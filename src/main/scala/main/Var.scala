package main

class Var[T](private val v: T) extends Emiter {
  private var value: T = v

  def !(v: T) = {
    //println("set " + value + "->" + v)
    value = v
    emit()
  }

  def ! = {
    //println("get: " + value)
    value
  }
}

object Var {
  def apply[T](a: T) = {
    new Var(a)
  }
}