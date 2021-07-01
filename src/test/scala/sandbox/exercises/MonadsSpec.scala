package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import cats.{Id, Monad}
import cats.syntax.flatMap._
import cats.syntax.functor._

class MonadsSpec extends AnyFreeSpec with Matchers {

  def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] = {
    a.flatMap(x => b.map(y => x*x + y*y))
  }

  "sumSquare should work" in {
    import cats.instances.option._
    import cats.instances.list._

    sumSquare(Option(3), Option(4)) should be (Option(25))
    sumSquare(List(1, 2, 3), List(4, 5)) should be (List(17, 26, 20, 29, 25, 34))
    sumSquare(3: Id[Int], 4: Id[Int]) should be (25)
  }

}
