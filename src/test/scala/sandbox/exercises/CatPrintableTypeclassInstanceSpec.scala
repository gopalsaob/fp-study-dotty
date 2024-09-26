package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.typeclasses.{Cat, Printable}
import sandbox.exercises.typeclasses.PrintableSyntax.*

class CatPrintableTypeclassInstanceSpec extends AnyFreeSpecLike with Matchers {

  "short demo app" in {
    val demoCat = Cat("Foo", 5, "Black")
    Printable.print(demoCat)
  }

  "short demo app2" in {
    Cat("Foo", 5, "Black").print
  }

  "cats Show" in {
    import cats.implicits.*

    println("Foo".show)
  }

}
