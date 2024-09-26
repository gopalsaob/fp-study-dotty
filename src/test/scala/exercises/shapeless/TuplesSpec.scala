package exercises.shapeless

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import tdd.TupleVals

class TuplesSpec extends AnyFreeSpecLike with Matchers {
  val tupleVals = new TupleVals
  import tupleVals.*
  "Tuple prepend should work as expected" in {
    t01.should(be(0L, 1, two, 3.3))
  }

  "Tuple append should work " in {
    t12.should(be(one, two, three, 4.4f, (5L, 6.6)))
  }

  "Tuple drop should work" in {
    t12.drop(3).should(be(t2))
  }

}
