resolvers ++= Seq(
  Classpaths.typesafeResolver,
  "oss sonatype" at "https://oss.sonatype.org/content/groups/public/",
  "digimead-maven" at "http://commondatastorage.googleapis.com/maven.repository.digimead.org/"
)

addSbtPlugin("org.digimead" % "sbt-dependency-manager" % "0.8.0.2-SNAPSHOT")

libraryDependencies += {

  val sbtV = (sbtBinaryVersion in update).value
  val scalaV = (scalaBinaryVersion in update).value
  val base = baseDirectory.value

  Defaults.sbtPluginExtra("org.digimead" % "sbt-osgi-manager" %
    scala.io.Source.fromFile(base / Seq("..", "version").mkString(java.io.File.separator)).mkString.trim, sbtV, scalaV)
}
