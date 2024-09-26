package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.functor.Functors

class FunctorsSpec extends AnyFreeSpecLike with Matchers {

  "Functors.doMath should work for options" in {
    import cats.instances.option.*
    Functors.doMath(Option(20)).should(be(Option(22)))
  }

  "Functors.doMath should work for lists" in {
    import cats.instances.list.*
    Functors.doMath(List(1, 2, 3)).should(be(List(3, 4, 5)))
  }

  "Function1.map using cats Functor created explicitly" in {
    import cats.Functor
    import cats.instances.function.*

    val func1 = (x: Int) => x.toDouble
    type F[A] = Int => A // partial unification, automatically done (left to right by compiler after SI-2712
    val func2 = (y: Double) => y * 2

    // The following is not required
    //    implicit val functorF: Functor[F] = new Functor[F] {
    //      override def map[A, B](fa: F[A])(f: A => B): F[B] = fa.andThen(f)
    //    }

    /*
    """
      |because of
      |  implicit def catsStdMonadForFunction1[T1]: Monad[T1 => *] =
      |    new Monad[T1 => *] {
      |      def pure[R](r: R): T1 => R = _ => r
      |
      |      def flatMap[R1, R2](fa: T1 => R1)(f: R1 => T1 => R2): T1 => R2 =
      |        t => f(fa(t))(t)
      |
      |      override def map[R1, R2](fa: T1 => R1)(f: R1 => R2): T1 => R2 =
      |        f.compose(fa)
      |
      |      def tailRecM[A, B](a: A)(fn: A => T1 => Either[A, B]): T1 => B =
      |        (t: T1) => {
      |          @tailrec
      |          def step(thisA: A): B = fn(thisA)(t) match {
      |            case Right(b)    => b
      |            case Left(nextA) => step(nextA)
      |          }
      |          step(a)
      |        }
      |    }
      |""".stripMargin
     */

    val func3 = Functor[F].map(func1)(func2)
    func3(2).should(be(4.0))
  }

  "cats Function1 Functor with partial unification should work in Scala 2.13+" in {
    import cats.instances.function.* // for Functor
    import cats.syntax.functor.*     // extension method for map

    val func1 = (x: Int) => x.toDouble
    val func2 = (y: Double) => y * 2
    val func3 = func1.map(func2)
    func3(2).should(be(4.0))
  }

  "cats partial unification should work for Either in Scala 2.13+" in {
    import cats.instances.either.*
    import cats.syntax.functor.*

    val either: Either[String, Int] = Right(123)
    either.fmap(_ + 1).should(be(Right(124)))
  }

  "Functor impl for Scalactic Or should work with custom implementation" in {
    import cats.Functor
    import org.scalactic.*

    // Scalactic Or is left biased so compiler cannot automatically use partial unification

    type Q[A] = Or[A, ?]

    implicit val functorQ: Functor[Q] = new Functor[Q] {
      override def map[A, B](fa: Q[A])(f: A => B): Q[B] = fa.map(f)
    }

    val o1: Q[String] = Good("Hello")
    val o2: Q[String] = Bad(One("BadMessage"))

    assert(
      (
        functorQ.fmap(o1)(_.length),
        functorQ.fmap(o2)(_.length)
      ) === (Good(5), Bad(One("BadMessage")))
    )
  }

  "Functor Impl for Scalactic Or with bad mapping" in {
    import cats.Functor
    import org.scalactic.*

    type F[A] = Or[?, A]

    implicit val functorF: Functor[F] = new Functor[F] {
      override def map[A, B](fa: F[A])(f: A => B): F[B] = fa.badMap(f)
    }

    val o1: F[Every[String]] = Good("Hello")
    val o2: F[Every[String]] = Bad(One("BadMessage"))

    //    o1.fmap(_.map(_.length)).should(be (Good("Hello"))) // Doesn't work -> Some regression or feature change in latest version of cats
    //    o2.fmap(_.map(_.length)).should(be (Bad(One(10))))
    assert(
      (
        functorF.fmap(o1)(_.map(_.length)),
        functorF.fmap(o2)(_.map(_.length))
      ) === (Good("Hello"), Bad(One(10)))
    )
  }

  "partial unification should not work for ContravariantFunctor which needs to fix Right type parameter" in {
    import cats.syntax.contravariant.*
    import cats.instances.function.*

    val func1 = (x: Int) => x.toDouble
    val func2 = (y: Double) => y.toString

    // Error:(128, 11) value contramap is not a member of Double => Double
    //    func2.contramap(func1).should(be (4.0)

    // Workaround for partial unification
    type <=[B, A] = A => B

    val func2B: <=[String, Double] = func2 // same as val func2B: String <= Double = func2

    // Contravariant[<=] same as Contravariant[Function1] because of the below generic code which works for any * => R

    /*
    """
      |  implicit def catsStdContravariantMonoidalForFunction1[R: Monoid]: ContravariantMonoidal[* => R] =
      |    new ContravariantMonoidal[* => R] {
      |      def unit: Unit => R = Function.const(Monoid[R].empty)
      |      def contramap[A, B](fa: A => R)(f: B => A): B => R =
      |        fa.compose(f)
      |      def product[A, B](fa: A => R, fb: B => R): ((A, B)) => R =
      |        (ab: (A, B)) =>
      |          ab match {
      |            case (a, b) => Monoid[R].combine(fa(a), fb(b))
      |          }
      |    }
      |""".stripMargin */

    // Note the above syntax of * => R is kind projection available through https://github.com/typelevel/kind-projector
    // and is equivalent to, type K[A] = A => R

    val func3c = func2B.contramap(func1)

    // the reason partial unification works is because
    // F[B, A] = A => B = say G[B][A]
    // with partial unification left side is constant
    // if f = implicit instance of Contravariant[G[String]]
    // func2B: G[String][Double] = func2
    // where R is double
    // func2B =
    // Contravariant[G[String]].contramap[Double, Int](f)(func2B: G[String][Double])(func1: Int => Double): G[String][Int]
    func3c(2).should(be("2.0"))
  }

  "Contravariant functor with explicit functor mapping should work" in {
    import cats.syntax.contravariant.*
    import cats.Contravariant

    type F[A] = A => String
    val func1            = (x: Int) => x.toDouble
    val func2: F[Double] = (y: Double) => y.toString

    implicit val contravariantFunctor: Contravariant[F] = new Contravariant[F] {
      override def contramap[A, B](fa: F[A])(f: B => A): F[B] = fa.compose(f)
    }

    val func3 = func2.contramap(func1)

    func3(2).should(be("2.0"))
  }
}
