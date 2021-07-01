import sbt._

object Dependencies {
  val circeVersion = "0.14.1"

  val dependencies =
    Seq(
//      compilerPlugin(
//        "org.typelevel" %% "kind-projector" % "0.11.3" cross CrossVersion.full
//      ), // Dotty has own syntax for type lambdas
//      compilerPlugin(
//        "org.augustjune" %% "context-applied" % "0.1.2"
//      )
    ) ++ Seq(
      "org.scalatest" %% "scalatest" % "3.2.9" % Test,
      "org.typelevel" %% "cats-core" % "2.6.1",
      "org.typelevel" %% "cats-effect" % "3.1.1" withSources() withJavadoc(),
//      "org.typelevel" %% "kittens" % "2.3.2", // wait for Scala 3
//      "dev.profunktor" %% "console4cats" % "0.8.1", // use cats-effects 3 instead
//      "org.manatki" %% "derevo-cats" % "0.10.5",
//      "org.manatki" %% "derevo-cats-tagless" % "0.10.5",
      "co.fs2" %% "fs2-core" % "3.0.4",
//      "com.olegpy" %% "meow-mtl-core" % "0.4.0",
//      "com.olegpy" %% "meow-mtl-effects" % "0.4.0",
//      "io.estatico" %% "newtype" % "0.4.3",
      "eu.timepit" %% "refined" % "0.9.26",
      "com.github.julien-truffaut" %% "monocle-core" % "3.0.0-M6",
      "com.github.julien-truffaut" %% "monocle-macro" % "3.0.0-M6",
//      "org.scala-steward" %% "scala-steward-core" % "0.8.1",
//      "com.softwaremill.retry" %% "retry" % "0.3.3"
    ) ++ Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion)
}
