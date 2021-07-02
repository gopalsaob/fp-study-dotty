//package exercises.shapeless
//
//sealed trait MyHList extends Product with Serializable
//
//final case class :::[+H, +T <: MyHList](head : H, tail : T) extends MyHList
//
//sealed trait MyHNil extends MyHList {
//  def :::[H](h : H): MyHList = exercises.shapeless.:::(h, this)
//}
//
//case object MyHNil extends MyHNil
//
//object MyHList {
//  def apply(): MyHList = MyHNil
//
//  // implicit conversion between MyHList and MyHListOps
//  implicit def hlistOps[L <: MyHList](l: L): MyHListOps[L] = new MyHListOps(l)
//
//}
//
//final class MyHListOps[L <: MyHList](l : L) {
//
//  /**
//   * Prepend the argument element to this `HList`.
//   */
//  def :::[H](h : H) : H ::: L = exercises.shapeless.:::(h, l)
//
//  def map[HF](f : HF)(implicit mapper : MyHListMapper[HF, L]) : mapper.Out = {
//    val _ = f
//    mapper(l)
//  }
//
//}
//
//
