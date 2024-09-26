package exercises

object Orderings {
//  trait Ord[T] {
//    def less: T => T => Boolean
//  }
//
//  implicit def __1: Ord[Int] = new Ord[Int] {
//    override def less: Int => Int => Boolean = x => y => x < y
//  }
//
//  implicit def __2[T](implicit o: Ord[T]): Ord[List[T]] = {
//    new Ord[List[T]] {
//      override def less: List[T] => List[T] => Boolean = xs => ys =>
//        (xs, ys) match {
//          case (_, Nil) => false
//          case (Nil, _) => true
//          case (x :: xtail, y :: ytail) if (x == y) => less(xtail)(ytail)
//          case (x :: _, y :: _) => isLess(x)(y)
//        }
//    }
//    }
//
//    def isLess[T]: T => T => Boolean = { x => y => implicit Ord[T] =>
//      implicitly[Ord[T]].less(x)(y)
//    }
}
