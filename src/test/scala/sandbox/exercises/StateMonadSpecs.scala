package sandbox.exercises

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import sandbox.exercises.monad.StateMonad

class StateMonadSpecs extends AnyFreeSpec with Matchers {

  "StateMonad for comprehension example should work" in {
    val (state, result) = StateMonad.program.run(1).value
    state should be (3)
    result should be ((1, 2, 3000))
  }

  "StateMonad using flatMap/map example should work" in {
    val (state, result) = StateMonad.program2.run(1).value
    state should be (3)
    result should be ((1, 2, 3000))
  }

}
