package sandbox.exercises.typeclasses

import cats.Eq

object Cat {
  import sandbox.exercises.typeclasses.PrintableIntances.*
  implicit val catPrintable: Printable[Cat] =
    new Printable[Cat] {
      override def format(input: Cat): String =
        s"${Printable.format(input.name)} is a ${Printable.format(input.age)} year-old ${input.color} cat"
    }

  implicit val catEq: Eq[Cat] = Eq.instance((c1, c2) => catsEquality(c1, c2))

  def catsEquality(c1: Cat, c2: Cat): Boolean = {
    import cats.implicits.*

    (c1.age === c2.age) && (c1.color === c2.color) && (c1.name === c2.name)
  }

  def tripleEquals(c1: Cat, c2: Cat): Boolean = {
    import cats.implicits.*

    c1 === c2
  }

  def tripleEqualsOpt(c1: Option[Cat], c2: Option[Cat]): Boolean = {
    import cats.implicits.*

    c1 === c2
  }

//  def tripleEqualGeneral[A](c1: A, c2: A): Boolean = {
  // TODO - understand def === which is implemented using macros
//  }
}

final case class Cat(name: String, age: Int, color: String)
