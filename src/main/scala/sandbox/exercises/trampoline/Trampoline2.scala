package sandbox.exercises.trampoline

sealed trait Trampoline2[A] {
  import Trampoline2.*
  final def runT: A =
    this match {
      case Done(result) => result
      case More(k)      => k().runT
    }

  final def flatMap[B](f: A => Trampoline2[B]): Trampoline2[B] =
    More(() => f(runT))

  final def map[B](f: A => B): Trampoline2[B] =
    flatMap(a => More(() => Done(f(a)))) // flatMap(f.andThenPure)
}

object Trampoline2 {
  case class Done[A](result: A)               extends Trampoline2[A]
  case class More[A](k: () => Trampoline2[A]) extends Trampoline2[A]
}
