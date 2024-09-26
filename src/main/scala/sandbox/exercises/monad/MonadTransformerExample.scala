package sandbox.exercises.monad

object MonadTransformerExample {
  import cats.data.OptionT

  type ListOption[A] = OptionT[List, A]

}

class OptionTImpl[F[_]: Monad, A] {}
