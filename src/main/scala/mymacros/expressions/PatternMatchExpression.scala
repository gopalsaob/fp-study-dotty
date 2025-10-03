package mymacros.expressions

import scala.quoted.*
import mymacros.inline.PowerCodeInline1.power

object PatternMatchExpression {
  def fusedPowCode(x: Expr[Double], n: Expr[Int])(using Quotes): Expr[Double] =
    x match
      case '{ power($y, $m) } => // we have (y^m) ^n
        fusedPowCode(y, '{ $n * $m }) // generate code for y^(n*m)
      case _ =>
        '{ power($x, $n) }
}
