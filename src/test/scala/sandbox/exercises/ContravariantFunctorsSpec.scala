package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.functor.ContravariantFunctor
import sandbox.exercises.testdata.Box

class ContravariantFunctorsSpec extends AnyFreeSpec with Matchers{
  implicit val stringPrintable: ContravariantFunctor[String, String] = (a: String) => "\"" + a + "\""
  implicit val booleanPrintable: ContravariantFunctor[Boolean, String] = (a: Boolean) => if (a) "yes" else "no"

  "ContravariantFunctors should work" in {

    ContravariantFunctor.format("hello") should be ("\"hello\"")
    ContravariantFunctor.format(true) should be ("yes")
  }

  "ContravariantFunctors for Box should work " in {

//    implicit def boxPrintable[A](implicit p: ContravariantFunctor[A, String]): ContravariantFunctor[Box[A], String] = {
//      (a: Box[A]) => p.f(a.value)
//    }

    implicit def boxPrintable[A](implicit p: ContravariantFunctor[A, String]): ContravariantFunctor[Box[A], String] = {
      p.contraMap2[Box[A]](_.value)(p)
    }

    ContravariantFunctor.format(Box("hello world")) should be ("\"hello world\"")
    ContravariantFunctor.format(Box(true)) should be ("yes")
  }

  "Using cats typeclasses should work" in {
    import cats.instances.string._
    import cats.Show
    import cats.Contravariant
    val showString = Show[String]

    // Contravariant.apply[Show] summons implicit instance of Contravariant[Show]
    val showSymbol: Show[Symbol] = Contravariant[Show].contramap[String, Symbol](showString)(sym => s"'${sym.name}")
    showSymbol.show(Symbol("dave")) should be ("'dave")
  }

  "Using cats contravariant extension method" in {
    import cats.instances.string._
    import cats.syntax.contravariant._
    import cats.Show
    val showString = Show[String]

    val showSymbol: Show[Symbol] = showString.contramap[Symbol](sym => s"'${sym.name}")
    showSymbol.show(Symbol("dave")) should be ("'dave")

  }

}
