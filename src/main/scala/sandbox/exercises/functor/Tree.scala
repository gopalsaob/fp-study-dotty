package sandbox.exercises.functor

import cats.Functor

object Tree {

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    def map[A, B](value: Tree[A])(func: A => B): Tree[B] = {
      value match {
        case Leaf(a) => Leaf(func(a))
        case Branch(left, right) => Branch(map(left)(func), map(right)(func))
      }
    }
  }

  // smart constructors for  tree

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)
}

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

case class Leaf[A](value: A) extends Tree[A]
