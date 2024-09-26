import sbt._

object Dependencies {
  val circeVersion = "0.14.10"

  val dependencies =
    Seq(
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.typelevel" %% "cats-core" % "2.12.0",
      "org.typelevel" %% "cats-effect" % "3.5.4" withSources() withJavadoc(),
      "co.fs2" %% "fs2-core" % "3.10.2",
      "eu.timepit" %% "refined" % "0.11.2",
      "com.github.julien-truffaut" %% "monocle-core" % "3.0.0-M6",
      "com.github.julien-truffaut" %% "monocle-macro" % "3.0.0-M6"
    ) ++ Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion)
}
