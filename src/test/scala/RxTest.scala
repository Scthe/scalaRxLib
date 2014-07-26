import main.{Obs, Rx, Var}
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

  @Test
  def test_obs = {
    val a = Var(3)
    val b = Rx{ (a!) * 5 } // 15

    var count = 0
    val o = Obs(a){
      count = (b!) + 1
    }
    assertEquals(16, count)
    a ! 4
    assertEquals(21, count)
  }

  @Test
  def test_option={
    val a = Var[Option[Int]](None)
    val b = Var[Option[Int]](None)
    val c = Rx {
      (a!).flatMap{ x =>
        (b!).map{ y =>
          x + y
        }
      }
    }
    a ! Some(1)
    b ! Some(2)
    //assert (c() == Some(3))
    assertEquals(Some(3), c!)
  }

  @Test
  def test_stringmul={
    val a = Var(Seq(1, 2, 3))
    val b = Var(3)
    val c = Rx{ (b!) +: (a!) }
    val d = Rx{ (c!).map("omg" * _) }
    val e = Var("wtf")
    val f = Rx{ ((d!) :+ (e!)).mkString }

    assert((f!) == "omgomgomgomgomgomgomgomgomgwtf")
    a ! Nil
    assert((f!) == "omgomgomgwtf")
    e ! "wtfbbq"
    assert((f!) == "omgomgomgwtfbbq")
  }

  @Test
  def test_pattern_match={
    val a = Var(1)
    val b = Var(2)
    val c = Rx{
      a.! match{
        case 0 => b!
        case x => x
      }
    }
    assertEquals(1, c!)
    a ! 0
    assertEquals(2, c!)
  }

}
