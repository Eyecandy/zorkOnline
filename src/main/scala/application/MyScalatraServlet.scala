package application

import org.scalatra._
import command._


class MyScalatraServlet extends ScalatraServlet {
  val myParser = new MyParser()
  get("/") {
    views.html.default()
  }
  post("/toMe"){
    val inputText:String = request.getParameter("input")
    myParser.frontEndParsing(inputText)
  }
}
