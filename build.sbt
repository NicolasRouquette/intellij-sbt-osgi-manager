import sbt.dependency.manager.{Keys => DMKeys, _}
import sbt.osgi.manager.{Keys => OMKeys, _}

DependencyManager

OSGiManagerWithDebug()

name := "intellij-sbt-osgi-manager"

version := "1.0"

scalaVersion := "2.12.2"

resolvers in OSGiConf +=
  typeP2("Eclipse Neon" at "http://builds.gradle.org:8000/eclipse/update-site/mirror/releases-neon/")

libraryDependencies in OSGiConf ++= Seq(
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.emf.ecore.xcore" % OSGi.ANY_VERSION  withSources),
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.xtext" % OSGi.ANY_VERSION  withSources),
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.emf.mwe2.runtime" % OSGi.ANY_VERSION  withSources),
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.uml2.uml" % OSGi.ANY_VERSION  withSources),
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.uml2.uml.resources" % OSGi.ANY_VERSION withSources)
)

commands += Command.command("resolveAndFetchOSGiDependencies") { s0 =>
  println("Executing resolveAndFetchOSGiDependencies...")
  val s1 = Command.process("osgiResolve", s0)
  val s2 = Project.runTask(DMKeys.dependencyTaskFetch, s1)
  s2.get._1
}

lazy val doResolveAndFetchOSGiDependencies = taskKey[Unit]("Execute resolveAndFetchOSGiDependencies command")

doResolveAndFetchOSGiDependencies := {
  println("Execute resolveAndFetchOSGiDependencies...")
  Command.process("resolveAndFetchOSGiDependencies", state.value)
}

update := update.dependsOn(doResolveAndFetchOSGiDependencies).value
