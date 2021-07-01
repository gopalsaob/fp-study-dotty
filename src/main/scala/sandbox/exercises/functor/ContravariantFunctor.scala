package sandbox.exercises.functor

trait ContravariantFunctor[A, T] {
  def f(a: A): T

  def contraMap[B](func: A => B)(functor1: ContravariantFunctor[B, T]): ContravariantFunctor[A, T] = {
    (a: A) => {
      functor1.f(func(a))
    }
  }

  def contraMap2[B](func: B => A)(functor1: ContravariantFunctor[A, T]): ContravariantFunctor[B, T] = {
    (b: B) => {
      functor1.f(func(b))
    }
  }

}

object ContravariantFunctor {
  def format[A](value: A)(implicit p: ContravariantFunctor[A, String]): String = p.f(value)
}