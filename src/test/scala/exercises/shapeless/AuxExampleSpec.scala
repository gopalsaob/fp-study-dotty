package exercises.shapeless

import exercises.shapeless.deptypes.AuxExample
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class AuxExampleSpec extends AnyFreeSpec with Matchers {

  "Aux should work " in {
    AuxExample.fooMonoid(1) must be ("")
    AuxExample.fooMonoid("Hello") must be (0)
  }

}
