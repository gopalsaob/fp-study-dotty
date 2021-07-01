package sandbox.exercises.monoid

import cats.Monoid
import sandbox.exercises.model.Order

object SuperAdder {

  def add(items: List[Int]): Int = {
    import cats.implicits._
    genericAdd(items)
  }

  def addOpt(items: List[Option[Int]]): Option[Int] = {
    import cats.implicits._
    genericAdd(items)
  }

  def addOrders(items: List[Order]): Order = {
//    https://github.com/typelevel/kittens/issues/209
//    import cats.derived._
//
//    implicit val m: Monoid[Order] = semi.monoid[Order]
//
    implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
      override def empty: Order = Order(0, 0)

      override def combine(x: Order, y: Order): Order = {
        Order(totalCost = x.totalCost + y.totalCost, quantity = x.quantity + y.quantity)
      }
    }

    genericAdd(items)
  }

  private def genericAdd[A](items: List[A])(implicit m: Monoid[A]): A = {
    items.foldLeft(m.empty)((a, b) => m.combine(a, b))
  }
}
