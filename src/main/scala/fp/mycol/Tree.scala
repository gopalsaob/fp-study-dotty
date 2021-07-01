package fp.mycol

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def size[A](tree: Tree[A]): Int =
    tree match {
      case Leaf(_) => 1
      case Branch(l, r) => 1 + size(l) + size(r)
    }

  def maximum(tree: Tree[Int]): Int =
    tree match {
      case Leaf(x) => x
      case Branch(l, r) => maximum(l) max maximum(r)
    }

  def depth[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(_) => 1
      case Branch(l, r) => 1 + (depth(l) max depth(r))
    }
  }

  def map[A, B](tree: Tree[A])(fn: A => B): Tree[B] = {
    tree match {
      case Leaf(x) => Leaf(fn(x))
      case Branch(l, r) => Branch(map(l)(fn), map(r)(fn))
    }
  }

  def fold[A, B](tree: Tree[A])(f: A => B)(g: (B, B) => B) : B = {
    tree match {
      case Leaf(x) => f(x)
      case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
    }
  }

  def sizeUsingFold[A](tree: Tree[A]): Int = fold(tree)(_ => 1)((x, y) => x + y + 1)

  def maximumUsingFold(tree: Tree[Int]): Int = fold(tree)(a => a)((x, y) => x max y)

  def depthUsingFold[A](tree: Tree[A]): Int = fold(tree)(_ => 1)((x, y) => 1 + (x max y))

  def mapUsingFold[A, B](tree: Tree[A])(fn: A => B): Tree[B] = fold(tree)(a => Leaf(fn(a)): Tree[B])((x, y) => Branch(x, y))
}