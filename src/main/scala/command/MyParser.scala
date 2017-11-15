package command

class MyParser {
  def startParsing() =  {
    val scanner = new java.util.Scanner(System.in)
    while (true) {
      val sc = scanner.nextLine()
      val commFunc: Option[String => String] = commands.commandMap.get(sc)
      commFunc match {
        case None => println("Invalid Command")
        case _ => {
          val successMessage:String = commFunc.get("s")
          println(successMessage)
        }
      }
    }
  }

  def frontEndParsing(input:String) = {
    val commFunc: Option[String => String] = commands.commandMap.get(input)
    commFunc match {
      case None => "Invalid Command"
      case _ => {
        val successMessage:String = commFunc.get("s")
        successMessage
      }
    }
  }
}
