package examples.giveninstances

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class GivenInstancesSpec extends AnyFreeSpec with Matchers {

  "GivenInstanes should be implicitly available" in {
    import examples.giveninstances.Example1.given
    import examples.giveninstances.Example1.*

    val k = summon[Ord[Int]]
    val k2 = summon[Ord[List[Int]]]

    k.compare(5, 3) should be (1)
    k2.compare(List(5, 3), List(5, 2)) should be (1)
  }

  "Negated given should work" in {
    import Example2._
    import Example2.given

    given Tagged[Int]() with {}

    summon[Foo[Int]].value should be (true)
    summon[Foo[String]].value should be (false)
  }
}