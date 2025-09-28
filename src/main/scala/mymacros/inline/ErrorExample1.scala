package mymacros.inline

import scala.compiletime.error

object ErrorExample1 {

  inline def div(n: Int, m: Int): Int =
    inline if m == 0 then error("Cannot divide by 0")
    else n / m

}
