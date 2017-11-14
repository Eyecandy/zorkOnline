import org.scalatra._
import javax.servlet.ServletContext

import io.muic.app.MyScalatraServlet

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new MyScalatraServlet, "/*")
  }
}
