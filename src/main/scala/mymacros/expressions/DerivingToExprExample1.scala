package mymacros.expressions

import scala.quoted.*

object DerivingToExprExample1 {

  given OptionToExpr[T: {Type, ToExpr}]: ToExpr[Option[T]] with
    def apply(opt: Option[T])(using Quotes): Expr[Option[T]] =
      opt match
        case Some(x) => '{ Some[T](${ Expr(x) }) }
        case None => '{ None }
  
}
