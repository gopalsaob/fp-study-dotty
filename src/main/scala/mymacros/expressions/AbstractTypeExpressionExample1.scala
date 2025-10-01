package mymacros.expressions

import scala.quoted.*

object AbstractTypeExpressionExample1 {

  def singletonListExpr[T: Type](x: Expr[T])(using Quotes): Expr[List[T]] =
    '{ List[T]($x) } // generic T used within a quote

  def emptyListExpr[T](using Type[T], Quotes): Expr[List[T]] =
    '{ List.empty[T] } // generic T used within a quote
}
