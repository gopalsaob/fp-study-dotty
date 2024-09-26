package sandbox.exercises

import cats.syntax.functor.*
import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.functor.Tree.*

class TreeFunctorSpec extends AnyFreeSpecLike with Matchers {

  "TreeFunctor should work" in {
    branch(leaf(10), leaf(20)).map(_ * 2).should(be(branch(leaf(20), leaf(40))))
  }

}
