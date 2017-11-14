package io.muic.app

import org.scalatra._


class MyScalatraServlet extends ScalatraServlet {

  get("/") {
    views.html.default()
  }
  post("/toMe"){
    val inputText:String = request.getParameter("input")
    val backEndOutPut = "backend received your command "
    val ret = backEndOutPut + inputText
    ret

  }

}
