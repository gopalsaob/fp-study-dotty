package sandbox.exercises.monad

import cats.Eval

object SafeRecursionUsingEval {

  def factorial(n: Int): Eval[BigInt] = {
    if (n == 1) {
      Eval.now(n)
    } else {
      Eval.defer(factorial(n - 1).map(_ * n)) // defer takes existing instance of Eval and defers its evaluation
    }
  }

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
    as match {
      case Nil => acc
      case head :: tail =>
        fn(head, foldRight(tail, acc)(fn))
    }
  }

  def safeFoldRight[A, B](as: List[A], b: B)(fn: (A, B) => B): B = {
    foldRightResultingInEval(as, Eval.now(b))(s(fn)).value
  }

  def foldRightResultingInEval[A, B](as: List[A], b: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] = {
    as match {
      case Nil => b
      case head :: tail =>
        Eval.defer(fn(head, foldRightResultingInEval(tail, b)(fn)))
    }
  }

  def s[A, B](fn: (A, B) => B): (A, Eval[B]) => Eval[B] = { (a, evalB) =>
    evalB.map(b => fn(a, b))
  }


  def foldRightTrampolined[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] = {
    as match {
      case Nil => acc
      case head :: tail =>
        Eval.defer(fn(head, foldRightTrampolined(tail, acc)(fn)))
    }
  }

  def foldRightEval[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
    foldRightTrampolined(as, Eval.now(acc)) { (a, evalB) =>
      evalB.map(b => fn(a, b))
    }.value
  }

}
