package book.examples.typeclass.jsonwriter.tc.interfaceobject

import book.examples.typeclass.jsonwriter.model.{Json => JsonModel}
import book.examples.typeclass.jsonwriter.tc.JsonWriter

object Json {

  def toJson[A](value: A)(implicit jsonWriter: JsonWriter[A]): JsonModel = {
    jsonWriter.write(value)
  }

}
