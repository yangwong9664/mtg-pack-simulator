import sbt.{Attributed, Credentials, Path, Resolver}

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

(Global / cancelable) := true

scalacOptions ++= Seq(
  "-release:8",
  "-encoding",
  "UTF-8",
  "-deprecation",
  "-feature",
  "-language:higherKinds",
  "-Ywarn-value-discard",
  "-Xfatal-warnings"
)

configs(IntegrationTest)
Defaults.itSettings

scalafmtOnCompile := true

(IntegrationTest / internalDependencyClasspath) += Attributed.blank((Test / classDirectory).value)

(IntegrationTest / fork) := true

enablePlugins(JavaServerAppPackaging, JavaAgent)

(Compile / mainClass) := Some("Main")

coverageFailOnMinimum := true
coverageMinimumStmtTotal := 88
coverageMinimumBranchTotal := 71

(Universal / javaOptions) ++= Seq(
  "-J-XX:+UseContainerSupport",
  "-J-XX:MinRAMPercentage=50.0",
  "-J-XX:MaxRAMPercentage=95.0",
)

lazy val root = (project in file("."))
  .settings(
    name := "mtg-pack-simulator"
  )

libraryDependencies ++= {

  val http4sBlazeVersion = "0.23.15"
  val http4sVersion = "0.23.23"
  val circeVersion = "0.14.6"
  val doobieVersion         = "1.0.0-RC2"
  val scalaPactVersion = "4.4.0"
  val testContainersVersion = "0.41.0"

  val mainDeps = Seq(
    "org.http4s" %% "http4s-blaze-server" % http4sBlazeVersion,
    "org.http4s" %% "http4s-circe" % http4sVersion,
    "org.http4s" %% "http4s-dsl" % http4sVersion,
    "org.http4s" %% "http4s-circe" % http4sVersion,
    "org.http4s" %% "http4s-blaze-client" % http4sBlazeVersion,
    "org.tpolecat" %% "doobie-core" % doobieVersion,
    "org.tpolecat" %% "doobie-hikari" % doobieVersion,
    "org.tpolecat" %% "doobie-postgres" % doobieVersion,
    "org.flywaydb" % "flyway-core" % "9.22.3",
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "com.beachape" %% "enumeratum-circe" % "1.7.3",
    "ch.qos.logback" % "logback-classic" % "1.2.12",
    "net.logstash.logback" % "logstash-logback-encoder" % "7.4",
    "ch.qos.logback.contrib" % "logback-jackson" % "0.1.5",
    "ch.qos.logback.contrib" % "logback-json-classic" % "0.1.5",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "org.postgresql"             % "postgresql"                         % "42.6.0",
    "io.circe" %% "circe-refined" % circeVersion,
    "org.tpolecat" %% "doobie-refined" % doobieVersion,
    "com.typesafe" % "config" % "1.4.3"
  )

  val testDeps = Seq(
    "com.h2database" % "h2" % "2.2.224" % "test",
    "org.tpolecat" %% "doobie-h2" % doobieVersion % "test,it",
    "org.tpolecat" %% "doobie-scalatest" % doobieVersion % "test,it",
    "com.dimafeng" %% "testcontainers-scala-scalatest" % testContainersVersion % "it",
    "com.dimafeng" %% "testcontainers-scala-postgresql" % testContainersVersion % "it",
    "org.scalatest" %% "scalatest" % "3.2.17" % "test,it",
    "com.itv" %% "scalapact-scalatest" % scalaPactVersion % "test,it",
    "com.itv" %% "scalapact-circe-0-14" % scalaPactVersion % "test,it",
    "com.itv" %% "scalapact-http4s-0-23" % scalaPactVersion % "test,it",
    "au.com.dius" % "pact-jvm-consumer-junit" % "4.0.10" % "test,it"
  )

  mainDeps ++ testDeps
}