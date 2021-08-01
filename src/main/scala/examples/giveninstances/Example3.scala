package examples.giveninstances

object Example3 {
  class GivenIntBox(using val givenInt: Int):
    def n = summon[Int]

  class GivenIntBox2(using givenInt: Int):
    given Int = givenInt
}
