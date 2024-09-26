package sandbox.cats2.simulacrum.generated

import cats.{Group, Monoid}
import cats.kernel.{Band, BoundedSemilattice, CommutativeGroup, CommutativeMonoid, Semilattice}
import scalaVersionSpecific.suppressUnusedImportWarningForScalaVersionSpecific

trait Invariant[F[_]] { self =>

  /**
   * Transform an `F[A]` into an `F[B]` by providing a transformation from `A`
   * to `B` and one from `B` to `A`.
   *
   * Example:
   * {{{
   * scala> import cats.implicits.*
   * scala> import scala.concurrent.duration.*
   * scala> val durSemigroup: Semigroup[FiniteDuration] =
   *      | Invariant[Semigroup].imap(Semigroup[Long])(Duration.fromNanos)(_.toNanos)
   * scala> durSemigroup.combine(2.seconds, 3.seconds)
   * res1: FiniteDuration = 5 seconds
   * }}}
   */
  def imap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B]

  def compose[G[_]: Invariant]: Invariant[λ[α => F[G[α]]]] =
    new ComposedInvariant[F, G] {
      val F = self
      val G = Invariant[G]
    }

  def composeFunctor[G[_]: Functor]: Invariant[λ[α => F[G[α]]]] =
    new ComposedInvariantCovariant[F, G] {
      val F = self
      val G = Functor[G]
    }

  def composeContravariant[G[_]: Contravariant]: Invariant[λ[α => F[G[α]]]] =
    new ComposedInvariantContravariant[F, G] {
      val F = self
      val G = Contravariant[G]
    }
}

trait ComposedCovariantContravariant[F[_], G[_]] extends Contravariant[λ[α => F[G[α]]]] { outer =>
  def F: Functor[F]
  def G: Contravariant[G]

  override def contramap[A, B](fga: F[G[A]])(f: B => A): F[G[B]] =
    F.map(fga)(ga => G.contramap(ga)(f))
}

trait ComposedInvariantCovariant[F[_], G[_]] extends Invariant[λ[α => F[G[α]]]] { outer =>
  def F: Invariant[F]
  def G: Functor[G]

  override def imap[A, B](fga: F[G[A]])(f: A => B)(g: B => A): F[G[B]] =
    F.imap(fga)(ga => G.map(ga)(f))(gb => G.map(gb)(g))
}

trait ComposedInvariantContravariant[F[_], G[_]] extends Invariant[λ[α => F[G[α]]]] { outer =>
  def F: Invariant[F]
  def G: Contravariant[G]

  override def imap[A, B](fga: F[G[A]])(f: A => B)(g: B => A): F[G[B]] =
    F.imap(fga)(ga => G.contramap(ga)(g))(gb => G.contramap(gb)(f))
}

trait ComposedInvariant[F[_], G[_]] extends Invariant[λ[α => F[G[α]]]] { outer =>
  def F: Invariant[F]
  def G: Invariant[G]

  override def imap[A, B](fga: F[G[A]])(f: A => B)(g: B => A): F[G[B]] =
    F.imap(fga)(ga => G.imap(ga)(f)(g))(gb => G.imap(gb)(g)(f))
}

trait ComposedFunctor[F[_], G[_]] extends Functor[λ[α => F[G[α]]]] with ComposedInvariant[F, G] { outer =>
  def F: Functor[F]
  def G: Functor[G]

  override def map[A, B](fga: F[G[A]])(f: A => B): F[G[B]] =
    F.map(fga)(ga => G.map(ga)(f))
}

@suppressUnusedImportWarningForScalaVersionSpecific
object Invariant {
  implicit val catsInvariantMonoid: Invariant[Monoid] = new Invariant[Monoid] {

    def imap[A, B](fa: Monoid[A])(f: A => B)(g: B => A): Monoid[B] = new Monoid[B] {
      val empty                                                     = f(fa.empty)
      def combine(x: B, y: B): B                                    = f(fa.combine(g(x), g(y)))
      override def combineAllOption(bs: IterableOnce[B]): Option[B] =
        fa.combineAllOption(bs.iterator.map(g)).map(f)
    }

  }

  implicit val catsInvariantBand: Invariant[Band] = new Invariant[Band] {

    def imap[A, B](fa: Band[A])(f: A => B)(g: B => A): Band[B] = new Band[B] {
      def combine(x: B, y: B): B                                    = f(fa.combine(g(x), g(y)))
      override def combineAllOption(bs: IterableOnce[B]): Option[B] =
        fa.combineAllOption(bs.iterator.map(g)).map(f)
    }
  }

  implicit val catsInvariantSemilattice: Invariant[Semilattice] = new Invariant[Semilattice] {

    def imap[A, B](fa: Semilattice[A])(f: A => B)(g: B => A): Semilattice[B] = new Semilattice[B] {
      def combine(x: B, y: B): B                                    = f(fa.combine(g(x), g(y)))
      override def combineAllOption(bs: IterableOnce[B]): Option[B] =
        fa.combineAllOption(bs.iterator.map(g)).map(f)
    }

  }

  implicit val catsInvariantCommutativeMonoid: Invariant[CommutativeMonoid] = new Invariant[CommutativeMonoid] {

    def imap[A, B](fa: CommutativeMonoid[A])(f: A => B)(g: B => A): CommutativeMonoid[B] = new CommutativeMonoid[B] {
      val empty                                                     = f(fa.empty)
      def combine(x: B, y: B): B                                    = f(fa.combine(g(x), g(y)))
      override def combineAllOption(bs: IterableOnce[B]): Option[B] =
        fa.combineAllOption(bs.iterator.map(g)).map(f)
    }

  }

  implicit val catsInvariantBoundedSemilattice: Invariant[BoundedSemilattice] = new Invariant[BoundedSemilattice] {

    def imap[A, B](fa: BoundedSemilattice[A])(f: A => B)(g: B => A): BoundedSemilattice[B] = new BoundedSemilattice[B] {
      val empty                                                     = f(fa.empty)
      def combine(x: B, y: B): B                                    = f(fa.combine(g(x), g(y)))
      override def combineAllOption(bs: IterableOnce[B]): Option[B] =
        fa.combineAllOption(bs.iterator.map(g)).map(f)
    }

  }

  implicit val catsInvariantGroup: Invariant[Group] = new Invariant[Group] {

    def imap[A, B](fa: Group[A])(f: A => B)(g: B => A): Group[B] = new Group[B] {
      val empty                                                     = f(fa.empty)
      def combine(x: B, y: B): B                                    = f(fa.combine(g(x), g(y)))
      def inverse(b: B): B                                          = f(fa.inverse(g(b)))
      override def combineAllOption(bs: IterableOnce[B]): Option[B] =
        fa.combineAllOption(bs.iterator.map(g)).map(f)
    }

  }

  implicit val catsInvariantCommutativeGroup: Invariant[CommutativeGroup] = new Invariant[CommutativeGroup] {

    def imap[A, B](fa: CommutativeGroup[A])(f: A => B)(g: B => A): CommutativeGroup[B] = new CommutativeGroup[B] {
      val empty                                                     = f(fa.empty)
      def combine(x: B, y: B): B                                    = f(fa.combine(g(x), g(y)))
      def inverse(b: B): B                                          = f(fa.inverse(g(b)))
      override def combineAllOption(bs: IterableOnce[B]): Option[B] =
        fa.combineAllOption(bs.iterator.map(g)).map(f)
    }

  }

  def apply[F[_]](implicit instance: Invariant[F]): Invariant[F] = instance

  trait Ops[F[_], A] {
    def typeClassInstance: Invariant[F]
    def self: F[A]
    def imap[B](f: A => B)(g: B => A): F[B] = typeClassInstance.imap(self)(f)(g)
  }

  trait ToInvariantOps {
    implicit def toInvariantOps[F[_], A](target: F[A])(implicit tc: Invariant[F]): Ops[F, A] =
      new Ops[F, A] {
        val self              = target
        val typeClassInstance = tc
      }
  }
  object nonInheritedOps extends ToInvariantOps

  trait AllOps[F[_], A] extends Ops[F, A] {
    def typeClassInstance: Invariant[F]
  }
  object ops {
    implicit def toAllInvariantOps[F[_], A](target: F[A])(implicit tc: Invariant[F]): AllOps[F, A] =
      new AllOps[F, A] {
        val self              = target
        val typeClassInstance = tc
      }
  }
}
