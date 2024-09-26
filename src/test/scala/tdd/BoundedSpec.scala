package tdd

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import tdd.dependenttypes.Utils.Bounded

class BoundedSpec extends AnyFreeSpecLike with Matchers {

  "Bounded should work as expected" in {
//    val zero15: Bounded[1,5] = 0      // ERROR
    val one15: Bounded[1, 5]   = 1
    val two15: Bounded[1, 5]   = 2
    val three15: Bounded[1, 5] = 3
    val four15: Bounded[1, 5]  = 4
    val five15: Bounded[1, 5]  = 5
//    val six15: Bounded[1,5] = 6       // ERROR
    assert(
      (one15, two15, three15, four15, five15) === (1, 2, 3, 4, 5)
    )
  }

}
