package examples.giveninstances

object Example2 {

  import scala.util.NotGiven

  trait Tagged[A]

  case class Foo[A](value: Boolean)
  object Foo:
    given fooTagged[A](using Tagged[A]): Foo[A]              = Foo(true)
    given fooNotTagged[A](using NotGiven[Tagged[A]]): Foo[A] = Foo(false)

}
