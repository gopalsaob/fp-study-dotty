package examples.typevariance

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class ListCoVarianceSpec extends AnyFreeSpec with Matchers {

  "Calico <: Cat, so List[Calico] <: List[Cat]" in {
    """
      | val listC1: List[Cat] = Animal.listCC1
      |""".stripMargin should compile
  }

  "Cat <: Animal, so List[Cat] cannot be assigned List[Animal]" in {
    """
      | val listC1: List[Cat] = Animal.listA1
      |""".stripMargin shouldNot compile
  }

}
