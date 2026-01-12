name := "play-redis"

version := "1.0.0"

scalaVersion := "2.13.18"

libraryDependencies ++= Seq(
  guice,
  "redis.clients" % "jedis" % "7.2.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)