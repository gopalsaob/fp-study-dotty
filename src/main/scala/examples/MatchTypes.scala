package examples

object MatchTypes {

  type Concat[Xs <: Tuple, +Ys <: Tuple] <: Tuple = Xs match
    case EmptyTuple => Ys
    case x *: xs    => x *: Concat[xs, Ys]
}
