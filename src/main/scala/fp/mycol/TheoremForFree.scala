package fp.mycol

import cats.Monad

object TheoremForFree {

  def map(fa: List[Int])(f: Int => String): List[String] =
    List("foo")

  def map[A, B, F[_]: Monad](fa: F[A])(f: A => B): F[B] = {
    val monadA = summon[Monad[F]]
    monadA.flatMap(fa)(a => monadA.pure(f(a)))
  }

//  def flatMap[A, B](a: F[A])(f: a -> F[B]): F[B]
//
//  def pure[A](value: A): F[A]
}
