package examples.typevariance

trait Animal

trait Cat extends Animal

trait Dog extends Animal

class Husky extends Dog

class Calico extends Cat

object Animal {

  val cc1 = Calico()
  val h1 = Husky()
  val c1: Cat = cc1
  val d1: Dog = h1
  val listCC1: List[Calico] = List(cc1)
  val listA1: List[Animal] = List(c1, d1)

}

