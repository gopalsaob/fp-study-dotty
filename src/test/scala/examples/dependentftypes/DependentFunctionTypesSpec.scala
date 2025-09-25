package examples.dependentftypes

import org.scalatest.freespec.AnyFreeSpecLike

class DependentFunctionTypesSpec extends AnyFreeSpecLike {

  "DependentFunctionTypes Example1" in {
    assert(Example1.foo1 == "t=3")
  }

  "DependentFunctionTypes Example2" in {
    assert(Example1.foo2 == "u=3")
  }

}
