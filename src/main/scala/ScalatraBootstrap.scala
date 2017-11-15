import org.scalatra._
import javax.servlet.ServletContext

import application.MyScalatraServlet

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new MyScalatraServlet, "/*")
  }
}
