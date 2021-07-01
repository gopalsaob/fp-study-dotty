package sandbox.exercises.monoid

object IntMonoid {


  object Add extends Monoid[Int] {

    override def combine(x: Int, y: Int): Int = x + y

    override def empty: Int = 0
  }

  //  Subtract is not a monoid because it is not associative
//  object Subtract extends Monoid[Int] {
//
//    override def combine(x: Int, y: Int): Int = x - y
//
//    override def empty: Int = 0
//  }
  /**
   * 9, 5, 2
   *
   *   m.combine(x, m.combine(y, z)) == 9 - (5 - 2) = 6
   *   m.combine(m.combine(x, y), z) == (9 - 5) - 2  = 2
   */

}
