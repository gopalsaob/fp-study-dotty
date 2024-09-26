//package exercises.shapeless
//
//import shapeless.Poly1
//
//object PolyEx1 {
//  import scala.math.Numeric
//
//  object total extends Poly1 {
//    implicit def base[A](implicit num: Numeric[A]):
//    Case.Aux[A, Double] =
//      at(num.toDouble)
//
//    implicit def option[A](implicit num: Numeric[A]):
//    Case.Aux[Option[A], Double] =
//      at(opt => opt.map(num.toDouble).getOrElse(0.0))
//
//    implicit def list[A](implicit num: Numeric[A]):
//    Case.Aux[List[A], Double] =
//      at(list => num.toDouble(list.sum))
//  }
//
//}
