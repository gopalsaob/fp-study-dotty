package mymacros.inline

object InlinePatternMatching {
  transparent inline def half(inline x: Any): Any =
    inline x match
      case x: Int => x / 2
      case x: Double => x / 2.0d

//  half(1.0d) // expands to: 1.0d / 2.0d
//  half(2) // expands to: 2 / 2
//  val n: Any = 3
//  half(n) // error: n is not statically known to be an Int or a Double
}
