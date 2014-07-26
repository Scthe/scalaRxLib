package main

class Var[T](private val v: T) {
  private var value: T = v

  def !(v: T) = {
    //println("set " + value + "->" + v)
    value = v
  }

  def ! = value
}

object Var {
  def apply[T](a: T) = {
    new Var(a)
  }
}

class Rx[T](block: () => T) {
  def ! = block()
}

object Rx {
  def apply[T](a: => T) = {
    new Rx(() => {
      a
    })
  }

  def main(args: Array[String]) {
    val a = Var(1)
    val b = Var(2)
    //var dd = a!
    val c = Rx {
      //a() + b()
      println("block: " + (a !) + "_" + (b !))
      (a !) + (b !)
      //a! + b!
      //!a + !b
    }
    println(c !) // 3
    a ! 4
    println(c !) // 6
  }
}