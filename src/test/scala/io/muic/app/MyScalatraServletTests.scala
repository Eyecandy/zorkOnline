package io.muic.app

import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike

class MyScalatraServletTests extends ScalatraSuite with FunSuiteLike {

  addServlet(classOf[MyScalatraServlet], "/*")

  test("GET / on MyScalatraServlet should return status 200"){
    get("/"){
      status should equal (200)
    }
  }

}
