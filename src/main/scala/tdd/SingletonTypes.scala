package tdd

object SingletonTypes {
  val one: 1 = 1
//  val notOne: 1 = 2 // will result in compilation error.

  import scala.compiletime.ops.int.S

  type K = S[0]
  val s1  = valueOf[K]
  val s2a = valueOf[S[S[0]]]
  val s2b = valueOf[S[1]]

}
