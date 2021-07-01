package sandbox.exercises.monad

import cats.data.State

object PostOrderCalculatorUsingStateMonad {
  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = {
    sym match {
      case "+" => binaryOperator(_ + _)
      case "*" => binaryOperator(_ * _)
      case "-" => binaryOperator(_ - _)
      case "/" => binaryOperator(_ / _)
      case num => operand(num.toInt)
    }
  }

  private def operand(num: Int): CalcState[Int] = {
    State[List[Int], Int] { s =>
      (num :: s, num)
    }
  }

  private def binaryOperator(f: (Int, Int) => Int): CalcState[Int] = {
//    State.modify[List[Int]] {
//      case h1 :: h2 :: tail =>
//        val result: Int = f(h1, h2)
//        result :: tail
//      case _ => throw new IllegalArgumentException("Invalid operand state")
//    } flatMap { _ =>
//      State.inspect[List[Int], Int](_.head)
//    }
    State[List[Int], Int] {
      case b :: a :: tail =>
        val result: Int = f(a, b)
        (result :: tail, result)
      case _ => throw new IllegalArgumentException("Invalid operand state")
    }
  }

  def evalAll(input: List[String]): CalcState[Int] = {
    import cats.syntax.applicative._

    input.foldLeft(0.pure[CalcState])((a, b) =>
      a.flatMap(_ => evalOne(b))
    )
  }
}
