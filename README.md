# Using sbt-osgi-manager with Intellij IDEA 2017.1.2 

Using SBT to build Scala/Java projects that depend on Eclipse P2-published features is painful.
With sbt-osgi-manager, it seems that this could be greatly simplified, e.g., from [build.sbt](build.sbt):

```sbt
resolvers in OSGiConf +=
  typeP2("Eclipse Neon" at "http://builds.gradle.org:8000/eclipse/update-site/mirror/releases-neon/")

libraryDependencies in OSGiConf ++= Seq(
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.emf.ecore.xcore" % OSGi.ANY_VERSION  withSources),
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.xtext" % OSGi.ANY_VERSION  withSources),
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.emf.mwe2.runtime" % OSGi.ANY_VERSION  withSources),
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.uml2.uml" % OSGi.ANY_VERSION  withSources),
  typeP2(OSGi.ECLIPSE_PLUGIN % "org.eclipse.uml2.uml.resources" % OSGi.ANY_VERSION withSources)
)

```

## Using the SBT shell

At the SBT shell, compiling & running this project involves the following:

```sbtshell
osgiResolve
dependencyFetch
compile
run
```

## Using IDEA 2017.1.2

This project imports fine; however, it fails to build with compilation errors:
 
```text
Information:4/24/17, 8:34 AM - Compilation completed with 6 errors and 0 warnings in 1s 620ms
/opt/local/imce/users/nfr/github.imce/intellij-sbt-osgi-manager/src/main/scala/Example.scala
Error:(3, 12) object eclipse is not a member of package org
import org.eclipse.xtext.resource.XtextResourceSet
Error:(4, 12) object eclipse is not a member of package org
import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup
Error:(5, 12) object eclipse is not a member of package org
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil
Error:(14, 5) not found: value XcoreStandaloneSetup
    XcoreStandaloneSetup.doSetup()
Error:(16, 18) not found: type XtextResourceSet
    val xs = new XtextResourceSet()
Error:(17, 5) not found: value UMLResourcesUtil
    UMLResourcesUtil.initLocalRegistries(xs)
``` 

When importing an SBT project, IDEA issues an SBT 'update' but this is insufficient.
IDEA doesn't know that it is necessary to execute:

```sbtshell
osgiResolve
dependencyFetch
```

- In IDEA's SBT Projects window, click on "Start SBT Shell" icon

  This creates a new "Run" window titled "Run SBT Shell"
  
- In the "SBT Shell" window in IDEA:


```sbtshell
osgiResolve
dependencyFetch
compile
run
```

This works; unfortunately, IDEA still does not know about the osgi dependencies.