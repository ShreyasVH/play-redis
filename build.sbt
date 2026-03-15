name := "play-redis"

version := "1.0.0"

scalaVersion := "3.8.2"

libraryDependencies ++= Seq(
  guice,
  "redis.clients" % "jedis" % "7.4.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)