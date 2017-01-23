//enablePlugins(ScalaJSPlugin)

name := "TronLikeSX root"

scalaVersion in ThisBuild := "2.11.8"

lazy val root = project.in(file(".")).
  aggregate().
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val project = crossProject.in(file(".")).
  settings(
    name := "TronLikeSX",
    version := "0.1-SNAPSHOT"
  ).
  jvmSettings(

  ).
  jsSettings(
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1"
  )

lazy val client = project.js
lazy val server = project.jvm


