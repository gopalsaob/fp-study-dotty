package fp

import fp.sample.Factorial.factorial
import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers

class FactorialSpec extends AnyFreeSpecLike with Matchers {

  "Factorial 5 " in {
    factorial(5) should be (120)
  }

}
