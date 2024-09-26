package examples.dependentftypes

object Example2 {
  trait Effect

  abstract class Fun[-X, +Y]:
    type Eff <: Effect
    def apply(x: X): Eff ?=> Y

  class CanThrow extends Effect
  class CanIO    extends Effect

  given ct: CanThrow = new CanThrow
  given ci: CanIO    = new CanIO

  class I2S extends Fun[Int, String]:
    type Eff = CanThrow
    def apply(x: Int) = x.toString

  class S2I extends Fun[String, Int]:
    type Eff = CanIO
    def apply(x: String) = x.length

  def map[A, B](f: Fun[A, B])(xs: List[A]): f.Eff ?=> List[B] =
    xs.map(f.apply)

  def mapFn[A, B]: (f: Fun[A, B]) => List[A] => f.Eff ?=> List[B] =
    f => xs => map(f)(xs)

  def compose[A, B, C](f: Fun[A, B])(g: Fun[B, C])(x: A): f.Eff ?=> g.Eff ?=> C =
    g(f(x))

  def composeFn[A, B, C]: (f: Fun[A, B]) => (g: Fun[B, C]) => A => f.Eff ?=> g.Eff ?=> C =
    f => g => x => compose(f)(g)(x)

  @main def test =
    val i2s = new I2S
    val s2i = new S2I

    assert(mapFn(i2s)(List(1, 2, 3)).mkString == "123")
    assert(composeFn(i2s)(s2i)(22) == 2)
}
