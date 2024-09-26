package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpecLike
import sandbox.exercises.functor.ContravariantFunctor
import sandbox.exercises.testdata.Box

class ContravariantFunctorsSpec extends AnyFreeSpecLike {
  given ContravariantFunctor[String, String]  = (a: String) => "\"" + a + "\""
  given ContravariantFunctor[Boolean, String] = (a: Boolean) => if (a) "yes" else "no"

  "ContravariantFunctors should work" in {
    assert(
      (
        ContravariantFunctor.format("hello"),
        ContravariantFunctor.format(true)
      ) === ("\"hello\"", "yes")
    )
  }

  "ContravariantFunctors for Box should work " in {

//    implicit def boxPrintable[A](implicit p: ContravariantFunctor[A, String]): ContravariantFunctor[Box[A], String] = {
//      (a: Box[A]) => p.f(a.value)
//    }

    implicit def boxPrintable[A](implicit p: ContravariantFunctor[A, String]): ContravariantFunctor[Box[A], String] =
      p.contraMap2[Box[A]](_.value)(p)

    assert(
      (
        ContravariantFunctor.format(Box("hello world")),
        ContravariantFunctor.format(Box(true))
      ) === ("\"hello world\"", "yes")
    )
  }

  "Using cats typeclasses should work" in {
    import cats.instances.string.*
    import cats.Show
    import cats.Contravariant
    val showString = Show[String]

    // Contravariant.apply[Show] summons implicit instance of Contravariant[Show]
    val showSymbol: Show[Symbol] = Contravariant[Show].contramap[String, Symbol](showString)(sym => s"'${sym.name}")
    assert(showSymbol.show(Symbol("dave")) === "'dave")
  }

  "Using cats contravariant extension method" in {
    import cats.instances.string.*
    import cats.syntax.contravariant.*
    import cats.Show
    val showString = Show[String]

    val showSymbol: Show[Symbol] = showString.contramap[Symbol](sym => s"'${sym.name}")
    assert(showSymbol.show(Symbol("dave")) === "'dave")
  }

}
