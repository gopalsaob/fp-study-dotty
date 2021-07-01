package book.examples.typeclass.jsonwriter.tc
import book.examples.typeclass.jsonwriter.model.{JsNull, JsNumber, JsObject, JsString, Json, Person}

object JsonWriterInstances {

  implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
    override def write(value: String): Json = JsString(value)
  }

  implicit val doubleWriter: JsonWriter[Double] = new JsonWriter[Double] {
    override def write(value: Double): Json = JsNumber(value)
  }

  implicit val personWriter: JsonWriter[Person] = new JsonWriter[Person] {
    override def write(value: Person): Json =
      JsObject(
        Map(
          "name" -> JsString(value.name),
          "email" -> JsString(value.email)
        )
      )
  }

  implicit def optionWriter[A](implicit jsonWriterForA: JsonWriter[A]): JsonWriter[Option[A]] = new JsonWriter[Option[A]] {
    override def write(value: Option[A]): Json = {
      value match {
        case Some(a) => jsonWriterForA.write(a)
        case None => JsNull
      }
    }
  }

}
