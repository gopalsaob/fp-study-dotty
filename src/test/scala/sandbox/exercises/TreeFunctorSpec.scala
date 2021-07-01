package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import cats.syntax.functor._
import sandbox.exercises.functor.Tree._

class TreeFunctorSpec extends AnyFreeSpec with Matchers {

  "TreeFunctor should work" in {
    branch(leaf(10), leaf(20)).map(_ * 2) should be ( branch(leaf(20), leaf(40)))
  }

}
