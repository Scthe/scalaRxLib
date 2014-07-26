package main

trait Receiver {
  def receive()
}

trait Emiter {
  private var listeners: List[Receiver] = Nil

  protected def emit() {
    //println("emit to:" + listeners.length)
    listeners.foreach(_.receive())
  }

  def +=(r: Receiver) {
    //println("register receiver")
    r.receive()
    listeners = r :: listeners
  }
}