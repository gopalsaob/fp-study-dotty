package examples.typelambdas

import cats.Functor

object Example1 {

  def scala2[A[_, _], B, R](a: A[B, R], fr: R => B, functor: Functor[({ type AB[C] = A[B, C] })#AB]): A[B, B] =
    functor.map(a)(fr)

  def scala3[A[_, _], B, R](a: A[B, R], fr: R => B, functor: Functor[({ type AB = [C] =>> A[B, C] })#AB]): A[B, B] =
    functor.map(a)(fr)
}
