package sandbox

import cats.instances.string.*
import cats.syntax.semigroup.*

object Main extends App {
  println("Hello " |+| "Cats!")
}
