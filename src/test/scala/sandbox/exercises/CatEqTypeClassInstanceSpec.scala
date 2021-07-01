package sandbox.exercises

import cats.kernel.Eq
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.typeclasses.{BEq, Cat, Z}

class CatEqTypeClassInstanceSpec extends AnyFreeSpec with Matchers {

  "Cat eq should work" in {
    import sandbox.exercises.typeclasses.CatEqInstance._

    val cat1 = Cat("Garfield",   38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")
    Eq[Cat].eqv(cat1, cat2) should be (false)
    Cat.tripleEquals(cat1, cat2) should be (false)
  }

  "Option[Cat] eq should work" in {
    import sandbox.exercises.typeclasses.CatEqInstance._
    import cats.implicits._

    val cat1 = Cat("Garfield",   38, "orange and black")
    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]
    Eq[Option[Cat]].eqv(optionCat1, optionCat2) should be (false)
    Cat.tripleEqualsOpt(optionCat1, optionCat2) should be (false)
  }

  "BEq should work" in {
    BEq.zEquality(new Z()) should be (true)
    type s = scala.Int.type
    println(List[s](Int, Int))
    type m[A] = cats.Eq[A]
    println(List[m[Int]]())
  }

  "foo should work" in {
    trait M[A] {
      type sType = A
    }
    trait D {
      this: M[_] =>
      type d = this.sType
    }

  }

}
