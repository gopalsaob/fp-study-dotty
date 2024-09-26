package examples

object RankNTypes {

  def apply[A](f: A => List[A], a: A): List[A] = f(a) // rank 1 type

//  def apply[A,B](f: A => List[A], b: B, s: String): (List[B], List[String]) =
//    (f(b), f(s)) // Rank 2 type

  def apply[B](f: [A] => A => List[A], b: B, s: String): (List[B], List[String]) =
    (f(b), f(s)) // Rank 2 type

  trait ~>[F[_], G[_]] {
    def apply[A](a: F[A]): G[A]
  }

  type Id[A] = A

  val singletonList = new (Id ~> List) {
    def apply[A](a: A): List[A] = List(a)
  }

  def apply[B](f: Id ~> List, b: B, s: String): (List[B], List[String]) =
    (f(b), f(s))

}
