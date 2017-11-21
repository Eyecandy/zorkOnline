package application

import org.scalatra._
import command._


class MyScalatraServlet extends ScalatraServlet {
  get("/") {
    views.html.default()
  }
  post("/toMe"){
    val inputText:String = request.getParameter("input")
    MyParser.frontEndParsing(inputText)
  }
}
