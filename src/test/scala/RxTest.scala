import main.{Rx, Var}
import org.junit.Assert._
import org.junit.Test

class RxTest {

  @Test
  def test1 = {
    val a = Var(1)
    val b = Var(2)
    val c = Rx {
      //println("block: " + (a !) + "_" + (b !))
      (a !) + (b !)
    }
    assertEquals(3, c !)
    a ! 4
    assertEquals(6, c !)
  }

  @Test
  def test_graph = {
    // https://raw.githubusercontent.com/lihaoyi/scala.rx/master/media/Intro.png
    val a = Var(1) // 1
    val b = Var(2) // 2

    val c = Rx{ (a!) + (b!) } // 3
    val d = Rx{ (c!) * 5 } // 15
    val e = Rx{ (c!) + 4 } // 7
    val f = Rx{ (d!) + (e!) + 4 } // 26
    assertEquals(3, c !)
    assertEquals(15, d !)
    assertEquals(7, e !)
    assertEquals(26, f !)
    a ! 3
    assertEquals(38, f !)
  }

  //@Test def test2 = assertFalse(true)

}
