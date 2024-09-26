package tdd

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import tdd.dependenttypes.DtVector

class DtVectorSpec extends AnyFreeSpecLike with Matchers {

  "DtVector should work " in {
    val dtVector = DtVector.fromVector(Vector(2, 17, 11))
    println(s"Printing DtVector = $dtVector")
//    dtVector.size.should( be (3))
  }

}
