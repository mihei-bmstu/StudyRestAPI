ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "2.13.8"

Test / parallelExecution := false

val akkaVersion = "2.6.19"
val akkaHttpVersion = "10.2.9"


lazy val root = (project in file("."))
  .settings(
    name := "StudyRestAPI",
    libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-stream" % akkaVersion,
        "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
        "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
        "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
        "com.pauldijou" %% "jwt-core" % "5.0.0",
        "org.scalatest" %% "scalatest" % "3.2.12" % Test,
        "com.typesafe.slick" %% "slick" % "3.3.3",
        "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
        "org.postgresql" % "postgresql" % "42.4.2",
        "org.slf4j" % "slf4j-nop" % "1.7.36",
        "org.flywaydb" % "flyway-core" % "9.1.6",
        "com.github.t3hnar" %% "scala-bcrypt" % "4.3.0",
        "com.lihaoyi" %% "scalatags" % "0.11.1"
        )
  )
