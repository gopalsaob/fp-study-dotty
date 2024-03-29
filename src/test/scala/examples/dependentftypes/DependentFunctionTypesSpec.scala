package examples.dependentftypes

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers

class DependentFunctionTypesSpec extends AnyFreeSpecLike with Matchers {

  "DependentFunctionTypes Example1" in {
    Example1.foo1.should(be ("t=3"))
    Example1.foo2.should(be ("u=3"))
  }

}
