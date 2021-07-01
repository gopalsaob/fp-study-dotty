package fp.sample

import scala.annotation.tailrec

object Fibonacci {

  def fibonacci(n: Int): Int = {

    @tailrec def fibTailRec(n: Int, prev2: Int, prev1: Int): Int = {
      if (n == 1) {
        0
      } else {
        fibTailRec(n - 1, prev1, prev1 + prev2)
      }
    }
    fibTailRec(n, 0, 1)
  }

  def isSorted[A](arr: Array[A], compare: (A, A) => Boolean): Boolean = {

    @tailrec
    def internal(n: Int): Boolean = {
      if (n > arr.length - 1) {
        true
      } else {
        !compare(arr(n), arr(n + 1)) && internal(n + 1)
      }
    }

    internal(0)
  }

  def main(args: Array[String]): Unit = {

    println(largest(List(2, 7, 4, 1, 3, 6), 0))

  }

  def sumOfIntegers(l: List[Int]) = l.sum

  @tailrec def sumOfIntegers(list: List[Integer], sum: Integer): Integer = {
    list match {
      case Nil => sum
      case x :: xs => sumOfIntegers(xs, sum + x)
    }
  }

  @tailrec def largest(l: List[Integer], result: Integer): Integer =
    l match {
      case Nil => result
      case x :: xs if x > result => largest(xs, x)
      case _ :: xs => largest(xs, result)
    }
}
