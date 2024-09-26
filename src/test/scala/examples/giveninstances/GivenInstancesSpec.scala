package examples.giveninstances

import org.scalatest.freespec.AnyFreeSpecLike
import scala.runtime.stdLibPatches.Predef.summon

class GivenInstancesSpec extends AnyFreeSpecLike {

  "GivenInstanes should be implicitly available" in {
    import examples.giveninstances.Example1.given
    import examples.giveninstances.Example1.*

    val k  = summon[Ord[Int]]
    val k2 = summon[Ord[List[Int]]]

    assert((k.compare(5, 3), k2.compare(List(5, 3), List(5, 2))) === (1, 1))
  }

  "Negated given should work" in {
    import Example2.*

    given Tagged[Int]() with {}

    assert(
      (summon[Foo[Int]].value, summon[Foo[String]].value) === (true, false)
    )

  }

  "Implicit class members should work" in {
    given g1: Int    = 5
    import Example3.*
    val givenIntBox1 = new GivenIntBox
    val givenIntBox2 = new GivenIntBox
    assert(
      (givenIntBox1.givenInt, givenIntBox2.givenInt) === (5, 5)
    )
  }

}
