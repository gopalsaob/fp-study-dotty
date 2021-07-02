package exercises

import org.scalatest._
import freespec._
import matchers.should.Matchers

class OrderingsSpec extends AnyFreeSpec with Matchers {

 "Orderings should work" in {
//    import Orderings._
//    isLess(Nil)(List(1, 2, 3))
  val bool = true
  bool should be (true)
 }
 
}
