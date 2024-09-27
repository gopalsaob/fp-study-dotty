package tdd

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import tdd.dependenttypes.*

class DtListSpec extends AnyFreeSpecLike with Matchers {

  "DtList should work as expected" in {
    val list = 1 +: "two" +: DTNil
    assert(
      (
        list.size,
        list.head,
        list.tail.size,
        list.tail.head
      ) === (2, 1, 1, "two")
    )
  }

  "DtList should result in compilation error if head on empty list" in {
    """
      | val list = 1 +: "two" +: DTNil
      | val s = list.tail.tail.head
      |""".stripMargin shouldNot compile
  }


}
