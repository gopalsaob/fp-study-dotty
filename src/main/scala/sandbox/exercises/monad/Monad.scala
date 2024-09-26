package sandbox.exercises.monad

trait Monad[F[_]] {

  def pure[A](value: A): F[A]

  def flatMap[A, B](value: F[A])(f: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] =
    flatMap(value)(func.andThen(pure))
//    flatMap(value)((pure _: A => F[A]).compose(func))

}
