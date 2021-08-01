package sandbox.exercises.testdata

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatest.matchers.should.Matchers
import scala.util.chaining.*

class TestingSomething extends AnyFreeSpecLike with Matchers {
  "foo " in {
    new C().pipe(_.f()).pipe(_.g())
  }
}

class C {
  def f(): C = {
    println("Hello")
    this
  }
  def g(): C = {
    println("World")
    this
  }
}
