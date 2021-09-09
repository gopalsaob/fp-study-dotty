package tdd

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import tdd.dependenttypes.Door
import tdd.dependenttypes.DoorState

class DoorSpecs extends AnyFreeSpecLike with Matchers {
  "Door open close should work as expected" in {
    val d1 = Door.mkDoor
    valueOf[d1.S].should( be (DoorState.Closed))

    val d2 = Door.open(d1)
    valueOf[d2.S].should( be (DoorState.Open))

//    val d3 = Door.open(d2)
  }
}


