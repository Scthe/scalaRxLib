package main

class Var[T](private val v: T) {
  private var value: T = v
  private var listeners: List[Receiver] = Nil

  def !(v: T) = {
    //println("set " + value + "->" + v)
    value = v
    emit()
  }

  def ! = {
    //println("get: " + value)
    value
  }

  def +=(r: Receiver) {
    //println("register receiver")
    r.receive()
    listeners = r :: listeners
  }

  private def emit() {
    //println("emit to:" + listeners.length)
    listeners.foreach(_.receive())
  }
}

object Var {
  def apply[T](a: T) = {
    new Var(a)
  }
}