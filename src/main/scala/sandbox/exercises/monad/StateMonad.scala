package sandbox.exercises.monad

import cats.Eval
import cats.data.IndexedStateT
import scala.annotation.tailrec

object StateMonad {
  import State.*
  import cats.data.State

  def program: State[Int, (Int, Int, Int)] =
    for {
      a <- get[Int]
      _ <- set[Int](a + 1)
      b <- get[Int]
      _ <- modify[Int](_ + 1)
      c <- inspect[Int, Int](_ * 1000)
    } yield (a, b, c)

  def program2: State[Int, (Int, Int, Int)] =
    get[Int] flatMap { a => // (1, 1)
      set[Int](a + 1) flatMap { _ => // (2, ())
        get[Int] flatMap { b => // (2, 2)
          modify[Int](_ + 1) flatMap { _ => // (3, 2)
            inspect[Int, Int](_ * 1000) map { c => // (3, 3000)
              (a, b, c)                            // 3, (1, 2, 3000)
            }
          }
        }
      }
    }

  def zipWithIndex2[A](list: List[A]): List[(Int, A)] =
    zipWithIndexUtil(list, Nil, 0)

  @tailrec
  private def zipWithIndexUtil[A](list: List[A], acc: List[(Int, A)], state: Int): List[(Int, A)] =
    list match {
      case Nil          => acc
      case head :: tail =>
        zipWithIndexUtil(tail, (state, head) :: acc, state + 1)
    }

  def zipWithIndexUsingState[A](list: List[A]): List[(Int, A)] =
    // list.foldLeft(State[Int, List[(Int, A)])
    list
      .foldLeft(pure[Int, List[(Int, A)]](Nil)) { (acc, a) =>
        acc.flatMap { xs =>
          get[Int] flatMap { n =>
            set[Int](n + 1) map { _ =>
              (n, a) :: xs
            }
          }
        }
      }
      .runA(0)
      .value

  def foo[A] = {
    val k: State[Int, List[(Int, A)]]                  = State.pure[Int, List[(Int, A)]](Nil)
    val j: IndexedStateT[Eval, Int, Int, Int]          = k flatMap { _ => get[Int] }
    val l: IndexedStateT[Eval, Int, Int, Unit]         = j flatMap { n => set[Int](n + 1) }
    val m: IndexedStateT[Eval, Int, Int, List[String]] = l.map(_ => List("Hello"))
    m.run(0)
  }

}
