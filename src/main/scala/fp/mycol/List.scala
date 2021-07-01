package fp.mycol

import scala.annotation.tailrec

sealed trait List[+A]
// `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  // `List` companion object. Contains functions for creating and working with lists.
  def sumRight(ints: List[Int]) = foldRight(ints, 0)(_ + _)
  def sumLeft(ints: List[Int]) = foldLeft(ints, 0)(_ + _)

  def productRightExplicit(ds: List[Double]): Double =
    ds match {
      case Nil => 1.0
      case Cons(x, xs) => x * productRightExplicit(xs)
    }
  def productRight(ds: List[Double]) = foldRight(ds, 1.0)(_ * _)
  def productLeft(ds: List[Double]) = foldLeft(ds, 1.0)(_ * _)

  def lengthRight[A](as: List[A]) = foldRight(as, 0)((_, count) => count + 1)
  def lengthLeft[A](as: List[A]) = foldLeft(as, 0)((_, count) => count + 1)

  def reverse[A](as: List[A]) = foldLeft(as, Nil: List[A])((h, xs) => Cons(h, xs))

//  def append[A](as: List[A], ns: List[A]): List[A] = {
//    as match {
//      case Nil => ns
//      case Cons(x, xs) => Cons(x, append(xs, ns))
//    }
//  }

  def append[A](as: List[A], ns: List[A]): List[A] = foldRight(as, ns)(Cons(_, _))

  def flatten[A](as: List[List[A]]): List[A] = foldRight(as, Nil: List[A])(append)

  @tailrec
  def flatten[A](as: List[List[A]], resultList: List[A]): List[A] =
    as match {
      case Nil => resultList
      case Cons(x, xs) => flatten(xs, append(resultList, x))
    }

  def addOne(as: List[Int]) = foldRight(as, Nil: List[Int])((a, b) => Cons(a + 1, b))

  @tailrec
  def addOne(as: List[Int], resultList: List[Int]): List[Int] =
    as match {
      case Nil => resultList
      case Cons(x, xs) => addOne(xs, Cons(x + 1, resultList))
    }

  def toStringList(as: List[Double]) = foldRight(as, Nil: List[String])((a, b) => Cons(a.toString, b))

//  def toStringList(as: List[Double]): List[String] =
//    as match {
//      case Nil => Nil: List[String]
//      case Cons(x, xs) => Cons(x.toString, toStringList(xs))
//    }

  def foldRightUsingFoldLeft[A, B](as: List[A], z: B)(fn: (A, B) => B): B =
    foldLeft(reverse(as), z)(fn)

  def foldRight[A, B](as: List[A], z: B)(fn: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => fn(x, foldRight(xs, z)(fn))
    }

  @tailrec def foldLeft[A, B](as: List[A], z: B)(fn: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, fn(x, z))(fn)
    }

  @tailrec def productRec(l: List[Double], product: Double): Double = {
    l match {
      case Nil => product
      case Cons(x, xs) => productRec(xs, x * product)
    }
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail[A](l: List[A]) =
    l match {
      case Nil => throw new IllegalArgumentException("Cannot tail an empty list")
      case Cons(_, t) => t
    }

  @tailrec def drop[A](l: List[A], n: Int): List[A] =
    l match {
      case _  if n.equals(0) => l
      case Nil => throw new IllegalArgumentException("Cannot tail an empty list")
      case Cons(_, t) => drop(t, n -1)
    }

  @tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
   l match {
     case Cons(h, t) if f(h) => dropWhile(t, f)
     case _ => l
   }

  def init[A](l: List[A]): List[A] = {
    l match {
      case Cons(_, Nil) => Nil
      case Cons(h, t) => Cons(h, init(t))
      case Nil => Nil
    }
  }

  def map[A,B](as: List[A])(f: A => B): List[B] = foldRight(as, Nil: List[B])((a, b) => Cons(f(a), b))

  def filter2[A](as: List[A])(f: A => Boolean): List[A] = foldRight(as, Nil: List[A])((a, b) => if (f(a)) Cons(a, b) else b)

//  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = foldRight(as, Nil: List[B])((a, b) => append(f(a), b))
  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = flatten(map(as)(f))

  def filter[A](as: List[A])(f: A => Boolean): List[A] = flatMap(as)(a => if (f(a)) List(a) else Nil)

  def addPairWise(l1: List[Int], l2: List[Int]): List[Int] =
    (l1, l2) match {
      case (Nil, Nil) => Nil
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (Cons(x, xs), Cons(y, ys)) => Cons(x + y, addPairWise(xs, ys))
    }

  def zipWith[A, B, C](l1: List[A], l2: List[B])(f: (A,B) => C): List[C] =
    (l1, l2) match {
      case (Nil, Nil) => Nil
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (Cons(x, xs), Cons(y, ys)) => Cons(f(x, y), zipWith(xs, ys)(f))
    }

  @tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = {
    sup match {
      case Nil => sub == Nil
      case _ if startsWith(sup, sub) => true
      case Cons(_, xs) => hasSubsequence(xs, sub)
    }
  }

  @tailrec
  def startsWith[A](sup: List[A], sub: List[A]): Boolean =
    (sup, sub) match {
      case (_, Nil) => true
      case (Cons(x, xs), Cons(y, ys)) if x == y => startsWith(xs, ys)
      case _ => false
    }
}
