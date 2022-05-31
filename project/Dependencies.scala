import sbt._

object Dependencies {
  val circeVersion = "0.14.2"

  val dependencies =
    Seq(
      "org.scalatest" %% "scalatest" % "3.2.12" % Test,
      "org.typelevel" %% "cats-core" % "2.7.0",
      "org.typelevel" %% "cats-effect" % "3.3.12" withSources() withJavadoc(),
      "co.fs2" %% "fs2-core" % "3.2.7",
      "eu.timepit" %% "refined" % "0.9.29",
      "com.github.julien-truffaut" %% "monocle-core" % "3.0.0-M6",
      "com.github.julien-truffaut" %% "monocle-macro" % "3.0.0-M6"
    ) ++ Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion)
}
