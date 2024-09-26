//package sandbox.exercises.ref
//
////import cats.effect.concurrent.Ref
//
//import scala.concurrent.duration.*
//import scala.concurrent.{Await, Future}
//import scala.util.Try
//
//object RetryCounter {
//
//  def myRetry[T](f: => Try[T]): (Long, Try[T]) = {
//    import scala.concurrent.ExecutionContext.Implicits.global
//    var counter = 0L
////    val counter: Ref[Int, F[_]] = Ref.of[F, Int](0)
//    val result = Await.result(
//      retry.Backoff(5).apply(() =>
//        Future {
//          counter = counter + 1
//          f
//        }
//      ),
//      5.seconds)
//    (counter, result)
//  }
//
//}
