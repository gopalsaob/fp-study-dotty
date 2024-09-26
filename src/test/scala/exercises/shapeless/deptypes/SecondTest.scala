//package exercises.shapeless.deptypes
//
//import org.scalatest.freespec.AnyFreeSpecLike
//import org.scalatest.matchers.should.Matchers
//
//class SecondTest extends AnyFreeSpecLike with Matchers {
//  import shapeless.*
//  import SecondSugar.*
//
//  "Second tests" in {
//    val second1 = Second[String :: Boolean :: Int :: HNil]
//    val result = second1("foo" :: true :: 123 :: HNil)
//    println(result)
//
//    val second2 = Second[Boolean :: String :: Int :: HNil]
//    val result2 = second2(true :: "foo" :: 123 :: HNil)
//    println(result2)
//  }
//
//}
