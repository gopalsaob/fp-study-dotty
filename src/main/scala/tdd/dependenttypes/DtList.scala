package tdd.dependenttypes

import scala.compiletime.ops.int.*

sealed trait DTList[N <: Int]:
  inline def size: N = valueOf[N]

  def +:[H](h: H): DTNonEmptyList[N, H, this.type] = DTNonEmptyList(h, this)

case object DTNil                                                        extends DTList[0]
case class DTNonEmptyList[N <: Int, H, T <: DTList[N]](head: H, tail: T) extends DTList[S[N]]
