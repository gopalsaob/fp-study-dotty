package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.functor.Codec
import sandbox.exercises.testdata.Box

class InvariantFunctorsSpec extends AnyFreeSpec with Matchers {

  implicit val stringCodec: Codec[String] = new Codec[String] {
    override def encode(value: String): String = value
    override def decode(value: String): String = value
  }

  implicit val intCodec: Codec[Int] = stringCodec.imap[Int](_.toInt, _.toString)
  implicit val boolCodec: Codec[Boolean] = stringCodec.imap[Boolean](_.toBoolean, _.toString)
  implicit val doubleCodec: Codec[Double] = stringCodec.imap[Double](_.toDouble, _.toString)
  implicit def boxCodec[A](implicit valueCodec: Codec[A]): Codec[Box[A]] =
    stringCodec.imap[Box[A]](x => Box(valueCodec.decode(x)), x => valueCodec.encode(x.value) )

  "Codec instances should work" in {
    import sandbox.exercises.functor.Codec._

    encode(123.4) should be ("123.4")
    decode[Double]("123.4") should be (123.4)
    encode(Box(123.4)) should be ("123.4")
    decode[Box[Double]]("123.4") should be (Box(123.4))
  }

  "Using cats, create Monoid of Symbol" in {
    import cats.Monoid
    import cats.Invariant
    import cats.instances.string._ // For Monoid
//    import cats.syntax.invariant._ // Extension method For imap
    import cats.syntax.semigroup._ // For |+|

    implicit val symbolMonoid: Monoid[Symbol] = Invariant[Monoid].imap(Monoid[String])(Symbol.apply)(_.name)
//    implicit val symbolMonoid: Monoid[Symbol] = Monoid[String].imap(Symbol.apply)(_.name)

    (Symbol("a") |+| Symbol("few") |+| Symbol("words")).name should be ("afewwords")
  }

}
