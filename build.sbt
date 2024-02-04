ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "scala-web-template",
    idePackagePrefix := Some("tech.mayboroda"),

    libraryDependencies ++= Seq(
      "com.twitter" %% "finagle-http" % "22.4.0",
      "ch.qos.logback" % "logback-classic" % "1.2.6",
    )
  )
