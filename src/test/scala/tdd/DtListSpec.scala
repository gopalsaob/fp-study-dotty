package tdd

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import tdd.dependenttypes.*

class DtListSpec extends AnyFreeSpecLike with Matchers {

  "DtList should work as expected" in {
    val list = 1 +: "two" +: DTNil

    list.size.should( be(2))
    list.head.should( be(1))
    list.tail.size.should( be(1))
    list.tail.head.should( be("two"))
//    val s = list.tail.tail.head // compilation error
//    println(s)
  }

}
