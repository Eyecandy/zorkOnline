package command

object MyParser {
  def startParsing() = {
    val scanner = new java.util.Scanner(System.in)
    while (true) {
      val sc = scanner.nextLine()
      val treatedInput = sc.toLowerCase().trim().split(" ")
      println("> " + sc)
      val commFunc: Option[String => String] = Commands.commandMap.get(treatedInput.head)
      commFunc match {
        case None => println("Invalid Command")
        case _ => {
          val successMessage: String = helper(treatedInput, commFunc.get)
          println(successMessage)
        }
      }
    }
  }

  def frontEndParsing(input: String) = {
    val treatedInput = input.toLowerCase().trim().split(" ")

    val commandFunc: Option[String => String] = Commands.commandMap.get(treatedInput.head)
    commandFunc match {
      case None => "Invalid Command"
      case _ => {
        val successMessage: String = helper(treatedInput, commandFunc.get)
        successMessage
      }
    }
  }

  private def helper(treatedInput: Array[String], cmdFunc: String => String): String = {
    val inputLength = treatedInput.length

    inputLength match {
      case 1 => cmdFunc(treatedInput.head)
      case 2 => {
        val arg = treatedInput.tail.head
        cmdFunc(arg)
      }
      case 4 =>  {
        exctractArg(treatedInput,cmdFunc)
      }
      case _ => "Invalid command"
    }
  }

  private def exctractArg(arg:Array[String],cmdFunc: String => String): String = {
    if (arg.tail.tail.head.equals("with")) {
      cmdFunc(arg.tail.head + "-" + arg.tail.tail.tail.head)
    }
    else {
      "Invalid command"
    }

  }
}
