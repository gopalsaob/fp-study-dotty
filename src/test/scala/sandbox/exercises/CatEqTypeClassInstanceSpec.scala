package sandbox.exercises

import cats.kernel.Eq
import org.scalatest.freespec.AnyFreeSpecLike
import sandbox.exercises.typeclasses.{BEq, Cat, Z}

class CatEqTypeClassInstanceSpec extends AnyFreeSpecLike {

  "Cat eq should work" in {
    import sandbox.exercises.typeclasses.CatEqInstance.*

    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")
    assert(
      (Eq[Cat].eqv(cat1, cat2), Cat.tripleEquals(cat1, cat2)) === (false, false)
    )
  }

  "Option[Cat] eq should work" in {
    import sandbox.exercises.typeclasses.CatEqInstance.*
    import cats.implicits.*

    val cat1       = Cat("Garfield", 38, "orange and black")
    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]
    assert(
      (
        Eq[Option[Cat]].eqv(optionCat1, optionCat2),
        Cat.tripleEqualsOpt(optionCat1, optionCat2)
      ) === (false, false)
    )
  }

  "BEq should work" in {
    type s    = scala.Int.type
    println(List[s](Int, Int))
    type m[A] = cats.Eq[A]
    println(List[m[Int]]())
    assert(BEq.zEquality(new Z()) === true)
  }

//  "foo should work" in {
//    trait M[A] {
//      type sType = A
//    }
//    trait D {
//      this: M[?] =>
//      type d = this.sType
//    }
//
//  }

}
