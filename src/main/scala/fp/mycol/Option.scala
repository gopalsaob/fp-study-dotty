package fp.mycol

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] =
    this match {
      case None    => None
      case Some(x) => Some(f(x))
    }

  def getOrElse[B >: A](default: => B): B =
    this match {
      case None    => default
      case Some(x) => x
    }

  infix def flatMap[B](f: A => Option[B]): Option[B] =
    map(f).getOrElse(None)
//    this match {
//      case None => None
//      case Some(x) => f(x)
//    }

  def map2[B](f: A => B): Option[B] =
    flatMap(a => Some(f(a)))

  def orElse[B >: A](ob: => Option[B]): Option[B] =
    map(a => Some(a)).getOrElse(ob)
//    this match {
//      case None => ob
//      case _ => this
//    }

  def filter(f: A => Boolean): Option[A] =
    flatMap(a => if (f(a)) Some(a) else None)
//    this match {
//      case Some(x) if f(x) => Some(x)
//      case _ => None
//    }
}

case class Some[+A](get: A) extends Option[A]
case object None            extends Option[Nothing]
