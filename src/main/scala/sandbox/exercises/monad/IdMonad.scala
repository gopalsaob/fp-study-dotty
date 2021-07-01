package sandbox.exercises.monad

object IdMonad {
  type Id[A] = A

  implicit val idMonad: Monad[Id] = new Monad[Id] {
    override def pure[A](value: A): Id[A] = value

    override def flatMap[A, B](value: Id[A])(f: A => Id[B]): Id[B] = f(value)

    override def map[A, B](value: Id[A])(func: A => B): Id[B] = func(value)
  }
}
