package exercises.shapeless

import exercises.shapeless.PolyEx1.total
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class PolyEx1Spec extends AnyFreeSpec with Matchers {

  "total poly should work " in {
    val s = total(10)
    println(s)

  }

}
