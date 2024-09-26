package fp.sample

import scala.annotation.tailrec

object Factorial {
  def factorial(n: Int): Int = {

    @tailrec def fact(n: Int, result: Int): Int =
      if (n < 2) {
        result
      } else {
        fact(n - 1, n * result)
      }
    fact(n, 1)
  }

}
