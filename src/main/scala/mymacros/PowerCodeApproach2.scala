package mymacros

import scala.quoted.*

object PowerCodeApproach2 {

  /*
   * Macro function
   */
  inline def powerMacro(x: Double, inline n: Int): Double =
    ${ powerCode('{ x }, '{ n }) }

  /*
   * meta language function
   */
  def powerCode(x: Expr[Double], n: Expr[Int])(using Quotes): Expr[Double] =
    n.value match
      case Some(m) => unrolledPowerCode(x, m) // if n is a constant, i.e. statically known
      case None => '{ Math.pow(${ x }, ${ n }.toDouble) } // n is not a constant, so can't use Macro, default to Math.pow function

  def unrolledPowerCode(x: Expr[Double], n: Int)(using Quotes): Expr[Double] =
    if n == 0 then '{ 1.0 }
    else if n == 1 then x
    else '{ ${ x } * ${ unrolledPowerCode(x, n - 1) } }

}
