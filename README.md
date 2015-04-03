Scala Rx implementation test lib
================================

This is small experimental library used to play with the notion of *reactivity*. There are many like this one, but this one is mine!

## What does it do ?

The idea is to provide the variables that can change their state based on other variables. The trick is that the do this BY THEMSELF ! Let's see the example:

normal code:
```scala
var a = 1
val b = 2
val c = a + b
a = 100
println(c) // 3
```

reactive variables:
```scala
import main.{Rx, Var}
val a = Var(1)
val b = Var(2)
val c = Rx { a.! + b.! }
a ! 100
println(c!) // 102
```

See how **c** changed value even though we only modified **a** ?

**MAGIC !**


## How does it work ?

Probably just through looking on the syntax most of Scala users would guess, but the key element is providing block to the Rx constructor. Every time we read the value of Rx object we reevaluate the block. Note that You have to try to read the value of Rx in order to be informed about the change.


If You are interested read [Deprecating the Observer Pattern][odersky] or see [lihaoyi/scala.rx][lihaoyi.scala.rx] for more robust library.


[odersky]:http://infoscience.epfl.ch/record/148043/files/DeprecatingObserversTR2010.pdf
[lihaoyi.scala.rx]:https://github.com/lihaoyi/scala.rx
