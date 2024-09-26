package sandbox.cats2.simulacrum.generated

trait ComposedContravariant[F[_], G[_]] extends Functor[λ[α => F[G[α]]]] { outer =>
  def F: Contravariant[F]
  def G: Contravariant[G]

  override def map[A, B](fga: F[G[A]])(f: A => B): F[G[B]] =
    F.contramap(fga)(gb => G.contramap(gb)(f))
}

trait ComposedContravariantCovariant[F[_], G[_]] extends Contravariant[λ[α => F[G[α]]]] { outer =>
  def F: Contravariant[F]
  def G: Functor[G]

  override def contramap[A, B](fga: F[G[A]])(f: B => A): F[G[B]] =
    F.contramap(fga)(gb => G.map(gb)(f))
}

trait Contravariant[F[_]] extends Invariant[F] { self =>
  def contramap[A, B](fa: F[A])(f: B => A): F[B]
  override def imap[A, B](fa: F[A])(f: A => B)(fi: B => A): F[B] = contramap(fa)(fi)

  def compose[G[_]: Contravariant]: Functor[λ[α => F[G[α]]]] =
    new ComposedContravariant[F, G] {
      val F = self
      val G = Contravariant[G]
    }

  /**
   * Lifts natural subtyping contravariance of contravariant Functors.
   * could be implemented as contramap(identity), but the Functor laws say this is equivalent
   */
  def narrow[A, B <: A](fa: F[A]): F[B] = fa.asInstanceOf[F[B]]

  def liftContravariant[A, B](f: A => B): F[B] => F[A] = contramap(_: F[B])(f)

  override def composeFunctor[G[_]: Functor]: Contravariant[λ[α => F[G[α]]]] =
    new ComposedContravariantCovariant[F, G] {
      val F = self
      val G = Functor[G]
    }
}

object Contravariant {
  def apply[F[_]](implicit instance: Contravariant[F]): Contravariant[F] = instance

  trait Ops[F[_], A] {
    def typeClassInstance: Contravariant[F]
    def self: F[A]
    def contramap[B](f: B => A): F[B] = typeClassInstance.contramap(self)(f)
    def narrow[B <: A]: F[B]          = typeClassInstance.narrow(self)
  }

  trait ToContravariantOps {
    implicit def toContravariantOps[F[_], A](target: F[A])(implicit tc: Contravariant[F]): Ops[F, A] =
      new Ops[F, A] {
        val self: F[A]        = target
        val typeClassInstance = tc
      }
  }
  object nonInheritedOps extends ToContravariantOps

  trait AllOps[F[_], A] extends Ops[F, A] with Invariant.AllOps[F, A] {
    def typeClassInstance: Contravariant[F]
  }
  object ops {
    implicit def toAllContravariantOps[F[_], A](
        target: F[A]
    )(implicit tc: Contravariant[F]): Contravariant.AllOps[F, A] =
      new AllOps[F, A] {
        val self              = target
        val typeClassInstance = tc
      }
  }
}
