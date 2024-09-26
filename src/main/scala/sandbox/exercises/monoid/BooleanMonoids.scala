package sandbox.exercises.monoid

trait Semigroup[A] {
  def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}
object Monoid {
  def apply[A](implicit monoid: Monoid[A]) = monoid
}

/**
 * | x | y | result |
 * | F | F |  F     |
 * | F | T |  T     |
 * | T | F |  T     |
 * | T | T |  T     |
 */
object Or extends Monoid[Boolean] {

  override def combine(x: Boolean, y: Boolean): Boolean = x || y

  override def empty: Boolean = true
}

/**
 * | x | y | result |
 * | F | F |  F     |
 * | F | T |  F     |
 * | T | F |  F     |
 * | T | T |  T     |
 */
object And extends Monoid[Boolean] {

  override def combine(x: Boolean, y: Boolean): Boolean = x && y

  override def empty: Boolean = false
}

/**
 * | x | y | result |
 * | F | F |  F     |
 * | F | T |  T     |
 * | T | F |  T     |
 * | T | T |  F     |
 */
object XOr extends Monoid[Boolean] {
  override def empty: Boolean = false

  override def combine(x: Boolean, y: Boolean): Boolean =
    (x && !y) || (!x && y)
}

/**
 * | x | y | result |
 * | F | F |  T     |
 * | F | T |  F     |
 * | T | F |  F     |
 * | T | T |  T     |
 */
object XNor extends Monoid[Boolean] {
  override def empty: Boolean = true

  override def combine(x: Boolean, y: Boolean): Boolean =
    (x || !y) && (!x || y)
}

// Why not FalseSemigroup
object FalseSemigroup extends Semigroup[Boolean] {
  override def combine(x: Boolean, y: Boolean): Boolean = false
}

//FFF - OR, AND, XOR
//FFT - XNOR
//FTF -  XNOR, AND
//FTT - OR, XOR
//TFF - XNOR, AND
//TFT - OR, XOR
//TTF - XOR
//TTT - OR, AND, XNOR
