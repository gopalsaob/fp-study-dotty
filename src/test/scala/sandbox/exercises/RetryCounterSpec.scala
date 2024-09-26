//package sandbox.exercises
//
//import org.scalatest.freespec.AnyFreeSpecLike
//import org.scalatest.matchers.should.Matchers
//import sandbox.exercises.ref.RetryCounter
//
//import scala.util.{Failure, Success, Try}
//
//class RetryCounterSpec extends AnyFreeSpecLike with Matchers {
//
//  var counter = 0L
//  "RetryCounter should retry with correct counts" in {
//
//    val (count, result) = RetryCounter.myRetry(someTask)
//    result.get.should(be ("succeeded finally"))
//    count.should(be (4)
//
//  }
//
//  def someTask: Try[String] = {
//    counter = counter + 1
//    if (counter % 4 == 0) {
//      Success("succeeded finally")
//    } else {
//      Thread.sleep(100)
//      Failure(new Exception("Some 500 error"))
//    }
//  }
//
//}
