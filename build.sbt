name := "play-redis"

version := "1.0.0"

scalaVersion := "3.8.4"

libraryDependencies ++= Seq(
  guice,
  "redis.clients" % "jedis" % "7.5.2"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)