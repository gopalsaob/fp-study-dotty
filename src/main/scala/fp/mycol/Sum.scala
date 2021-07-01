package fp.mycol

trait Sum[A, B]

object Sum {
  case class Left[A, B](a: A) extends Sum[A, B]
  case class Right[A, B](b: B) extends Sum[A, B]
}