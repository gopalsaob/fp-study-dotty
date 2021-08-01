//package book.examples.typeclass.jsonwriter
//
//import book.examples.typeclass.jsonwriter.model.{JsObject, JsString, Person}
//import org.scalatest.freespec.AnyFreeSpecLike
//import org.scalatest.matchers.should.Matchers
//
//class JsonWriterSpec extends AnyFreeSpecLike with Matchers {
//
//  "Type class Interface using Interface Objects should work" in {
//    import book.examples.typeclass.jsonwriter.tc.JsonWriterInstances.*
//    import book.examples.typeclass.jsonwriter.tc.interfaceobject.Json
//
//    Json.toJson(Person("Dave", "dave@example.com")).should(be (JsObject(
//      Map(
//        "name" -> JsString("Dave"),
//        "email" -> JsString("dave@example.com")
//      )
//    ))
//  }
//
//  "Type class Interface using Interface Syntax should work" in {
//    import book.examples.typeclass.jsonwriter.tc.JsonWriterInstances.*
//    import book.examples.typeclass.jsonwriter.tc.interfacesyntax.JsonSyntax.*
//
//    Person("Dave", "dave@example.com").toJson.should(be (JsObject(
//      Map(
//        "name" -> JsString("Dave"),
//        "email" -> JsString("dave@example.com")
//      )
//    ))
//  }
//
//}
