package sandbox.exercises.functor

import cats.Functor
import cats.implicits.*

object Functors {

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 2)

}
