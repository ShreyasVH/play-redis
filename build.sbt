name := "play-redis"

version := "1.0.0"

scalaVersion := "3.8.1"

libraryDependencies ++= Seq(
  guice,
  "redis.clients" % "jedis" % "7.2.1"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)