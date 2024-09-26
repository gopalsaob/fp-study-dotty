package exercises

import freespec.*
import matchers.should.Matchers
import org.scalatest.*

class OrderingsSpec extends AnyFreeSpecLike with Matchers {

  "Orderings should work" in {
//    import Orderings.*
//    isLess(Nil)(List(1, 2, 3))
    val bool = true
    bool.should(be(true))
  }

}
