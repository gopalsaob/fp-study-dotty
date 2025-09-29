package mymacros

import mymacros.inline.PowerCodeInline1
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class MacrosSpec extends AnyFreeSpec with Matchers {

  "PowerCodeApproach1.powerMacro should work" in {
    PowerCodeApproach1.powerMacro(2, 5) should be (32)
  }

  "PowerCodeApproach2.powerMacro should work" in {
    PowerCodeInline1.power(2, 5) should be (32)
  }

}
