name := "play-redis"

version := "1.0.0"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  guice,
  "redis.clients" % "jedis" % "2.8.1"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)