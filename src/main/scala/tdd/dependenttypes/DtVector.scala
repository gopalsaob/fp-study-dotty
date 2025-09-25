package tdd.dependenttypes

import scala.Vector

case class DtVector[N <: Int, A](internalRep: Vector[A]):
  inline def size: N = valueOf[N]

object DtVector {

  def fromVector[A](vector: Vector[A]) = {
    val h = new Helper(vector.length)
    helper[A, h.value.type](vector)
  }

  def helper[A, T <: Singleton & Int](v: Vector[A]): DtVector[T, A] = DtVector[T, A](v)

}

case class Helper(val value: Int)
