package io.muic.app

import org.scalatra._


class MyScalatraServlet extends ScalatraServlet {

  get("/") {
    views.html.default()
  }
  post("/") {
    println (request.body)
  }

}
