package exercises.shapeless

/**
 * Type-specific case of a polymorphic unary function.
 *
 * @author Miles Sabin
 */
abstract class MyCase1Aux[-P, T] {
  type R
  val value : T => R
}

object MyCase1Aux {
  def apply[P, T, R0](v : T => R0) = new MyCase1Aux[P, T] {
    type R = R0
    val value = v
  }

  implicit def inst[P, T, R0](c : MyCase1Aux[P, T] { type R = R0 }) : T => R0 = c.value
}

/**
 * Base trait for polymorphic values.
 *
 * @author Miles Sabin
 */
trait MyPoly {

  /** The type of the case representing this polymorphic unary function at argument type `T`. */
  type Case1[T] = MyCase1Aux[this.type, T]

  /** Creates an instance of the case representing this polymorphic unary function at argument type `T`. */
  def case1[T] = new {
    def apply[R0](f : T => R0) = new Case1[T] {
      type R = R0
      val value = f
    }
  }

  /** The type of a case of this polymorphic function of the form `T => R` */
  type Pullback1[T, R0] = Case1[T] { type R = R0 }

  def apply[T](t : T)(implicit c : Case1[T]) : c.R = c.value(t)
}

/**
 * Provides implicit conversions from polymorphic function values to monomorphic function values, eg. for use as
 * arguments to ordinary higher order functions.
 *
 * @author Miles Sabin
 */
object MyPoly {
  implicit def inst1[P <: MyPoly, T](p : P)(implicit c : p.Case1[T]) : T => c.R = c.value

  type Pullback1Aux[-P, T, R0] = MyCase1Aux[P, T] { type R = R0 }
}


import MyTypeOperators._



/**
 * Base trait for natural transformations.
 *
 * @author Miles Sabin
 */
trait ~>[F[_], G[_]] extends MyPoly {
  import scala.language.reflectiveCalls

  def default[T](f : F[T]) : G[T]
  def apply[T](f : F[T]) = default(f)
  implicit def caseUniv[T] = case1[F[T]](default[T] _)
}

object ~> {
  implicit def inst1[G[_], T](p : Id ~> G) : T => G[T] = p.caseUniv[T].value
  implicit def inst2[F[_], G[_], T](p : F ~> G) : F[T] => G[T] = p.caseUniv[T].value
}

///** Polymorphic identity function. */
//object identity extends (Id ~> Id) {
//  def default[T](t : T) = t
//}

/** Polymorphic singleton function. */
object singleton extends (Id ~> Set) {
  def default[T](t : T) = Set(t)
}

///** Polymorphic function selecting an arbitrary element from a non-empty `Set`. */
//object choose extends (Set ~> Option) {
//  def default[T](s : Set[T]) = s.headOption
//}
//
///** Polymorphic function creating singleton `List`s. */
//object list extends (Id ~> List) {
//  def default[T](t : T) = List(t)
//}
//
///** Polymorphic function returning the head of a `List`. */
//object headOption extends (List ~> Option) {
//  def default[T](l : List[T]) = l.headOption
//}
//
///** Polymorphic function testing whether or not an `Option` is defined. */
//object isDefined extends (Option ~> Const[Boolean]#λ) {
//  def default[T](o : Option[T]) = o.isDefined
//}
//
///** Polymorphic function which opens an `Option`. */
//object get extends (Option ~> Id) {
//  def default[T](o : Option[T]) = o.get
//}
//
///** Polymorphic function which injects a value into an `Option`. */
//object option extends (Id ~> Option) {
//  def default[T](t : T) = Option(t)
//}
//
///** Polymorphic addition with type specific cases. */
//object plus extends Poly {
//  implicit val caseInt = case2[Int, Int](_ + _)
//  implicit val caseDouble = case2[Double, Double](_ + _)
//  implicit val caseString = case2[String, String](_ + _)
//  implicit def caseList[T] = case2[List[T], List[T]](_ ::: _)
//}
//
///** Polymorphic zero with type specific cases. */
//object zero extends Poly {
//  implicit val zeroInt = case0[Int](0)
//  implicit val zeroDouble = case0[Double](0.0)
//  implicit val zeroString = case0[String]("")
//  implicit def zeroList[T] = case0[List[T]](Nil)
//}
