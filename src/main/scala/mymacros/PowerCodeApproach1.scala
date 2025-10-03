package mymacros

import scala.quoted.*

object PowerCodeApproach1 {

  /*
   * Macro function
   */
  /** Specialize the operation based on static knowledge of the value of `n` */
  inline def powerMacro(x: Double, inline n: Int): Double =
    ${ powerCode('x, 'n) }

  /** If `n` is known, generate`'{ x * ... * x }` with`n` repetitions of `x` */
  private def powerCode(x: Expr[Double], n: Expr[Int])(using Quotes): Expr[Double] =
    unrolledPowerCode(x, n.valueOrAbort) // emits an error message if`n` is not a constant

  /**
   * Good practice.
   * 
   * Actual implementation in language rather than meta language.
   */
  private def unrolledPowerCode(x: Expr[Double], n: Int)(using Quotes): Expr[Double] =
    if n == 0 then '{ 1.0 }
    else if n == 1 then x
    else '{ $x * ${ unrolledPowerCode(x, n - 1) } }


}
