name := "fp-study-dotty"
version := "0.0.1-SNAPSHOT"

scalaVersion := "3.7.2"

libraryDependencies ++= Dependencies.dependencies

//scalacOptions ++= Seq("-source:future")

javacOptions ++= Seq("-source", "17")