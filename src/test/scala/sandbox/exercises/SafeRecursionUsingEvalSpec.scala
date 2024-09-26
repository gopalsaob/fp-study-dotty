package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.monad.SafeRecursionUsingEval

class SafeRecursionUsingEvalSpec extends AnyFreeSpecLike with Matchers {

  "Factorial should work without stackOverflowException" in {
    val x = SafeRecursionUsingEval.factorial(50000)
    println(x.value)
  }

  "FoldRight should work without stackOverflowException" in {
    val x  = SafeRecursionUsingEval.foldRightEval((1 to 100000).toList, 0L)(_ + _)
    println(x)
    val x2 = SafeRecursionUsingEval.safeFoldRight((1 to 100000).toList, 0L)(_ + _)
    println(x2)
  }

}
