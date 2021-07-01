package exercises.shapeless.deptypes

// Type Level programming example
object AuxExample {
  import cats.Monoid

  trait Foo[A] {
    type B
    def value: B
  }

  implicit def fi = new Foo[Int] {
    type B = String
    val value = "Foo"
  }
  implicit def fs = new Foo[String] {
    type B = Int
    val value = 5
  }

  def foo[T](t: T)(implicit f: Foo[T]): f.B = {
    println(t)
    f.value
  } // works !!!

//  def fooMonoid[T](t: T)(implicit f: Foo[T], m: Monoid[f.B]): f.B = m.empty// doesn't work, one parameter cannot depend on another parameter.

  // Solution: Aux Pattern

  type Aux[A0, B0] = Foo[A0] { type B = B0  }

  def fooMonoid[T, R](t: T)(implicit f: Aux[T, R], m: Monoid[R]): R = {
    println(f)
    println(t)
    m.empty
  } // works !!

}
