//package exercises.shapeless
//
//import org.scalatest.freespec.AnyFreeSpecLike
//import org.scalatest.matchers.should.Matchers
//
//class MyHListSpec extends AnyFreeSpecLike with Matchers {
//
//  "singleton map on List " in {
//    val fnMono: String => Set[String] = SingletonInstances.singletonString _
//
//    // syntactic sugar for
////    val fnMono2 = new Function1[String, Set[String]] {
////      def apply(v: String): Set[String] = HListPoly.singletonString(v)
////    }
//    List("foo", "bar", "baz") map fnMono.should(be (List(Set("foo"), Set("bar"), Set("baz")))
//
////    val fnPoly: Nothing => Set[Nothing] = HListPoly.singleton _
//    // In case of fnPoly, since there is no argument specified, it infers to Nothing and so result type is Set[Nothing]
//    // if we specify/fix the argument type, it is no longer polymorphic
//
//    // So function values are monomorhpic
//    // methods are seconds class polymorphic .. because eta expansion determines the parameter type during expansion
//    // so
//
//    List(1, 2, 3) map SingletonInstances.singleton.should(be (List(Set(1), Set(2), Set(3)))
//
//    // but methods are not first class polymorphic (because HListPoly.singleton will not work for HList.map
//
////    List("foo", "bar", "baz") map fnPoly.should(be (List(Set("foo"), Set("bar"), Set("baz")))
//
//    // the polymorphism of the FunctionN traits is fixed at the point at which they’re instantiated rather than the point at which they’re applied.
////    trait Function1[-T, +R] { def apply(t: T): R }
//
//    // Solution, experiment1 -> shift the type parameters from Function1 to apply method
////    trait PolyFunction1 {
////      def apply[T, R](t: T): R
////    }
//    // This does not work because the Result type R is unconstrained and problematic in terms of type inference.
//
//
////    trait PolyFunction1[F[_]] {
////      def apply[T](t: T): F[T]
////    }
//
//  }
//
//}
