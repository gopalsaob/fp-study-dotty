package examples.dependentftypes

object Example1 {
  trait C { type M; val m: M }

  type DF = (x: C) => x.M

  type IDF = (x: C) ?=> x.M

  val c = new C { type M = Int; val m = 3 }

  def foo1: String = {
    val depfun: DF = (x: C) => x.m
    val t          = depfun(c)
    s"t=$t"
  }

  def foo2: String = {
    val idepfun: IDF = summon[C].m
    val u            = idepfun(using c)
    s"u=$u"
  }
}
