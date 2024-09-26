package sandbox.exercises.typeclasses

object PrintableIntances {

  implicit val intPrintable: Printable[Int]       = (input: Int) => input.toString
  implicit val stringPrintable: Printable[String] = (input: String) => input
}

object Printable {

  def format[A](input: A)(implicit printable: Printable[A]): String =
    printable.format(input)

  def print[A](input: A)(implicit p: Printable[A]): Unit =
    println(format(input))
}

trait Printable[A] {

  def format(value: A): String

  def contraMap[B](func: B => A): Printable[B] = { (value: B) =>
    Printable.this.format(func(value))
  }
}
