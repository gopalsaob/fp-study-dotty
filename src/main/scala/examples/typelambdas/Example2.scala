package examples.typelambdas

object Example2 {
  type T[+X, Y] = Map[Y, X]

  type Tuple = [X] =>> (X, X)

  def test(): Unit = {

    val m: T[String, Int] = Map(1 -> "1")
    println(m)

    val tuple: Tuple[String] = ("a", "b")
    println(tuple)
  }
}
