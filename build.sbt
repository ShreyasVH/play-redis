name := "play-redis"

version := "1.0.0"

scalaVersion := "3.9.0"

libraryDependencies ++= Seq(
  guice,
  "redis.clients" % "jedis" % "8.0.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)