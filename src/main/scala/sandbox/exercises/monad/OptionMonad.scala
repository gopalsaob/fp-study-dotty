package sandbox.exercises.monad

import cats.{Monad => MonadC}

object OptionMonad {

  val optionMonad: MonadC[Option] = new MonadC[Option] {
    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = {
      fa.flatMap(f)
    }

    override def tailRecM[A, B](a: A)(f: A => Option[Either[A, B]]): Option[B] =
      f(a).flatMap(_.toOption)

    override def pure[A](x: A): Option[A] = Some(x)
  }

}
