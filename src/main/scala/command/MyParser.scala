package command

class MyParser {
  def startParsing() =  {

    val scanner = new java.util.Scanner(System.in)
    while (true) {
      val sc = scanner.nextLine()
      val treatedInput = sc.toLowerCase().trim()
      println(sc)
      val commFunc: Option[String => String] = commands.commandMap.get(treatedInput)
      commFunc match {
        case None => println("Invalid Command")
        case _ => {
          val successMessage:String = commFunc.get(treatedInput)
          println(successMessage)
        }
      }
    }
  }

  def frontEndParsing(input:String) = {
    val treatedInput = input.toLowerCase().trim()
    val commandFunc: Option[String => String] = commands.commandMap.get(treatedInput)
    commandFunc match {
      case None => "Invalid Command"
      case _ => {
        val successMessage:String = commandFunc.get(treatedInput)
        successMessage
      }
    }
  }
}
