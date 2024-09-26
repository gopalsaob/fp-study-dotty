package examples.typelambdas

import cats.Functor
import cats.implicits.*
import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers

class TypeLambdasExampleSpec extends AnyFreeSpecLike with Matchers {

  "TypeLambdas in Scala 2 and Scala 3 should be same" in {
    val m  =
      Map(
        5 -> "foo",
        7 -> "bar",
        2 -> "sil"
      )
    type MapInt = [A] =>> Map[Int, A]
    val f  = implicitly[Functor[MapInt]]
    val t  = Example1.scala2[Map, Int, String](m, _.length, f)
    val t2 = Example1.scala3[Map, Int, String](m, _.length, f)
    t2.should(be(t))
  }

  "Example2.should(compile" in {
    Example2.test().should(be(()))
  }
}
