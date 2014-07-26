package main

//https://github.com/lihaoyi/scala.rx

trait Receiver {
  def receive()
}

class Obs[T](block: () => Unit) extends Receiver {
  def receive() {
    //println("\t\treceived")
    block()
  }
}

object Obs {
  def apply[T](r: Var[T])(a: => Unit) = {
    val o = new Obs[T](() => {
      a
    })
    r += o
    o
  }
}


class Rx[T](block: () => T) {

  def ! = {
    block()
  }

}

object Rx {
  def apply[T](a: => T) = {
    new Rx(() => {
      a
    })
  }

  def main(args: Array[String]) {
    val a = Var(3)
    val b = Rx {
      println("RECALC rx: " + (a !))
      (a !) * 5
    } // 15

    var count = 0
    val o = Obs(a) {
      println("RECALC obs: " + (b !))
      count = (b !) + 1
    }
    println("\t" + count) // 16
    a ! 4
    println("\t" + count) // 21

  }
}