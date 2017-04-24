import java.lang.System

import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil

import scala.{Array, StringContext, Unit}
import scala.Predef.String

object Example {

  def main(argv: Array[String]): Unit = {

    XcoreStandaloneSetup.doSetup()

    val xs = new XtextResourceSet()
    UMLResourcesUtil.initLocalRegistries(xs)

    System.out.println(s"Example...")
  }
}
