package sandbox.exercises.ref

trait Retry[F[_]] {
  def apply[T](f: => F[T]): F[T]
}

