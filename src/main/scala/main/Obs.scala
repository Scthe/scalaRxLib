package main

class Obs(block: () => Unit) extends Receiver {
  def receive() {
    println("\t\treceived")
    block()
  }
}

object Obs {
  def apply(r: Emiter)(a: => Unit) = {
    val o = new Obs(() => {
      a
    })
    r += o
    o
  }
}