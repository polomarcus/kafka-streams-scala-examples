name := "kafka-streams-scala-examples"

organization := "com.github.polomarcus"

version := "0.0.1"

scalaVersion := "2.12.7"
val kafkfaVersion = "2.0.0"
val logback = "1.2.3"

scalacOptions := Seq("-Xexperimental", "-unchecked", "-deprecation")

// JSON
//Add Circe if needed

// logging
libraryDependencies += "ch.qos.logback" % "logback-classic" % logback
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"

// Kafka
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % kafkfaVersion
//Fix the weirdest bug ever, without this line we cannot get kafka-streams-scala
libraryDependencies += "javax.ws.rs" % "javax.ws.rs-api" % "2.1" artifacts( Artifact("javax.ws.rs-api", "jar", "jar")) // this is a workaround for https://github.com/jax-rs/api/issues/571
// change it : libraryDependencies += "org.apache.kafka" % "kafka-streams-test-utils" % kafkfaVersion % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % Test