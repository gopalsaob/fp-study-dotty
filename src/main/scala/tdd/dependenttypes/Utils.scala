package tdd.dependenttypes

import scala.compiletime.ops.int.-

object Utils {
  // Only allowed values are Min <= N <= Max.
  type Bounded[MIN <: Int, MAX <: Int] <: Int = MAX match
    case MIN => MIN
    case Any => MAX | Bounded[MIN, MAX - 1]

  // A type for allowed indices, zero-based, for an indexed collection of size N.
  type IndexOf[N <: Int] = Bounded[0, N - 1]
}
