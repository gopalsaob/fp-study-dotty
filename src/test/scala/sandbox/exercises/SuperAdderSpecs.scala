package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.model.Order
import sandbox.exercises.monoid.SuperAdder

class SuperAdderSpecs extends AnyFreeSpecLike with Matchers {

  "SuperAdder should work for Int" in {
    SuperAdder.add(List(1, 2, 4)).should(be(7))
  }

  "SuperAdder should work for Option of Int" in {
    SuperAdder.addOpt(List(Some(1), Some(2), Some(4), None)).should(be(Some(7)))
  }

  "SuperAdder should work for Orders" in {
    SuperAdder.addOrders(List(Order(1, 2), Order(2, 3))).should(be(Order(3, 5)))
  }

}
