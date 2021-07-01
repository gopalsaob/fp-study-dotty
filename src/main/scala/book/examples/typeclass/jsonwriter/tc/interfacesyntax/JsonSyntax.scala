package book.examples.typeclass.jsonwriter.tc.interfacesyntax

import book.examples.typeclass.jsonwriter.model.Json
import book.examples.typeclass.jsonwriter.tc.JsonWriter

object JsonSyntax {

  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit jsonWriter: JsonWriter[A]): Json = {
      jsonWriter.write(value)
    }
  }

}
