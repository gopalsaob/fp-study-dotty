//package tdd
//
//import org.scalatest.freespec.AnyFreeSpecLike
//import org.scalatest.matchers.should.Matchers
//
//class SingletonTypesSpec extends AnyFreeSpecLike with Matchers {
//
//  "SingletonTypes should work as expected" in {
//    SingletonTypes.one.should(be(valueOf[1]))
//  }
//
//  "Prove that the type 1 is subtype of Int" in {
//    summon[1 <:< Int](1).should(be(1))
//  }
//
//  "scala.compiletime.S should behave like Nat" in {
//    assert(
//      (
//        SingletonTypes.s1,
//        SingletonTypes.s2a,
//        SingletonTypes.s2b
//      ) === (1, 2, 2)
//    )
//  }
//
//}
