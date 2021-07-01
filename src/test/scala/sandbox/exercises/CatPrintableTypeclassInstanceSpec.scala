package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpec
import sandbox.exercises.typeclasses.PrintableSyntax._
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.typeclasses.{Cat, Printable}

class CatPrintableTypeclassInstanceSpec extends AnyFreeSpec with Matchers {

  "short demo app" in {
    val demoCat = Cat("Foo", 5, "Black")
    Printable.print(demoCat)
  }

  "short demo app2" in {
    Cat("Foo", 5, "Black").print
  }

  "cats Show" in {
    import cats.implicits._

    println("Foo".show)
  }

}
