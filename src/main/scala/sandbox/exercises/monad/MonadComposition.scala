package sandbox.exercises.monad

//import cats.syntax.applicative._
//import cats.FlatMap

import cats.implicits._
//import cats.syntax.monad._
import cats.Monad

object MonadComposition {

  def compose[M1[_]: Monad, M2[_]: Monad] = {
    type Composed[A] = M1[M2[A]]

    new Monad[Composed] {
      override def pure[A](x: A): Composed[A] = x.pure[M2].pure[M1]

      override def flatMap[A, B](fa: Composed[A])(f: A => Composed[B]): Composed[B] = {
        ???
      }

      override def tailRecM[A, B](a: A)(f: A => Composed[Either[A, B]]): Composed[B] = ???
    }
  }

}

object MonadComposition2 {

  def compose[M1[_]: Monad] = {
    type Composed[A] = M1[Option[A]]

    new Monad[Composed] {
      override def flatMap[A, B](fa: Composed[A])(f: A => Composed[B]): Composed[B] = {
        val t = implicitly[Monad[M1]]
        t.flatMap(fa) {
          case None => t.pure(Option.empty[B])
          case Some(a) => f(a)
        }
      }

//      def flatMap2[A, B](fa: Composed[A])(f: A => Composed[B]): Composed[B] = {
//        val t = implicitly[Monad[M1]]
//        t.flatMap(fa)(_.fold(t.pure(Option.empty[B]))(f))
//      }

      override def tailRecM[A, B](a: A)(f: A => Composed[Either[A, B]]): Composed[B] = {
        val t = implicitly[Monad[M1]]
        flatMap(pure(a))(a => t.map(f(a))(_.flatMap(_.toOption)))
      }

      override def pure[A](x: A): Composed[A] = implicitly[Monad[M1]].pure(Option(x))
    }
  }

}
