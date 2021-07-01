package exercises.shapeless

trait MyHListMapper[HF, In <: MyHList] {
  type Out <: MyHList
  def apply(in: In) : Out
}

trait MyHListMapperAux[HF, In <: MyHList, Out <: MyHList] {
  def apply(in: In) : Out
}

object MyHListMapper {
  implicit def mapper[HF, In <: MyHList, Out0 <: MyHList](implicit mapper : MyHListMapperAux[HF, In, Out0]) = new MyHListMapper[HF, In] {
    type Out = Out0
    def apply(in: In) : Out = mapper(in)
  }
}

object MyHListMapperAux {
  import MyPoly._

  implicit def hnilMapper1[HF] = new MyHListMapperAux[HF, MyHNil, MyHNil] {
    def apply(l : MyHNil) = MyHNil
  }

  implicit def hlistMapper1[HF <: MyPoly, InH, OutH, InT <: MyHList, OutT <: MyHList](
                                                                                       implicit hc : Pullback1Aux[HF, InH, OutH],
                                                                                       mt : MyHListMapperAux[HF, InT, OutT]) =
    new MyHListMapperAux[HF, InH ::: InT, OutH ::: OutT] {
      def apply(l : InH ::: InT) = hc.value(l.head) ::: mt(l.tail)
    }
}

