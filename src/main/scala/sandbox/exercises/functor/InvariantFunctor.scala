package sandbox.exercises.functor

trait Codec[A] {
  def encode(value: A): String
  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] =
    new Codec[B] {
      override def encode(value: B): String = Codec.this.encode(enc(value))

      override def decode(value: String): B = dec(Codec.this.decode(value))
    }
}

object Codec {

  def encode[A](value: A)(implicit c: Codec[A]): String = c.encode(value)
  def decode[A](value: String)(implicit c: Codec[A]): A = c.decode(value)

}
