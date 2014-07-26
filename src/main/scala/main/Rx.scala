package main

//https://github.com/lihaoyi/scala.rx

// TODO Rx should emit too
// problem: setting Var value does not provoke Rx refresh
// so any Rx callback do not fire
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
    a ! 4 // listener added to a forces Obs update
    println("\t" + count) // 21

  }
}