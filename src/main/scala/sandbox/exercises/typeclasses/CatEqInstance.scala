package sandbox.exercises.typeclasses

import cats.Eq

object CatEqInstance {

  implicit val eqCat: Eq[Cat] = Eq.instance((c1, c2) => catsEquality(c1, c2))

  def catsEquality(c1: Cat, c2: Cat): Boolean = {
    import cats.implicits.*

    (c1.age === c2.age) && (c1.color === c2.color) && (c1.name === c2.name)
  }
}

class Z {

  type r = Z.this.type

}

object BEq {
  def zEquality(z: Z): Boolean = {
    val l1 = List[z.r](z)
    val l2 = List[z.type](z)

    import cats.Eq
    implicit val eqListZ: Eq[List[z.type]] = Eq.fromUniversalEquals[List[z.type]]
    import cats.syntax.eq.*

    l1 === l2
  }
}
