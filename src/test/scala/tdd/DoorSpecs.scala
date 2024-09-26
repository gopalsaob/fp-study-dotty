package tdd

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import tdd.dependenttypes.Door
import tdd.dependenttypes.DoorState

class DoorSpecs extends AnyFreeSpecLike with Matchers {
  "Door should be closed initially" in {
    val d1 = Door.mkDoor
    valueOf[d1.S].should(be(DoorState.Closed))
  }

  "When Door is opened, it should be in Open state" in {
    val d1 = Door.mkDoor
    val d2 = Door.open(d1)
    valueOf[d2.S].should(be(DoorState.Open))
    //    val d3 = Door.open(d2)
  }
}
