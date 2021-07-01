package exercises.shapeless.circe

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import shapeless.{Generic, LabelledGeneric}

class CsvSpec extends AnyFreeSpec with Matchers {
  import cats.syntax.option._

  case class BigData(
      a: Option[Int], b: Option[Int], c: Option[Int], d: Option[Int], e: Option[Int], f: Option[Int], g: Option[Int], h: Option[Int], i: Option[Int], j: Option[Int],
      k: Option[Int], l: Option[Int], m: Option[Int], n: Option[Int], o: Option[Int], p: Option[Int], q: Option[Int], r: Option[Int], s: Option[Int], t: Option[Int],
      u: Option[Int], v: Option[Int], w: Option[Int])

  "foo" in {
    import io.circe.generic.auto._
    import io.circe.syntax._

    println("foo")
    val b: BigData = BigData(1.some, 2.some, 3.some, 4.some, 5.some, none[Int], None, 8.some, 9.some, 10.some, 11.some, 12.some, 13.some, 14.some, 15.some, 16.some, 17.some, 18.some, 19.some, 20.some, 21.some, 22.some, 23.some)
    val c = Generic[BigData].to(b)
    println("GOS: c = " + c)
    Generic[BigData].from(c) should be(b)

    val d = LabelledGeneric[BigData].to(b)
    println("GOS: d = " + d)
    Generic[BigData].from(d) should be(b)
    val t = b.asJson
    println("GOS: t = " + t)

  }

}
