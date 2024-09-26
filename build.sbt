name := "fp-study-dotty"
version := "0.0.1-SNAPSHOT"

scalaVersion := "3.5.1"

libraryDependencies ++= Dependencies.dependencies

scalacOptions ++= Seq("-source:future")

javacOptions ++= Seq("-source", "17")