package startgame


import command.MyParser
import factories.roomGenerator

class GameRunner {
  def initiateEverything() = {
    println("initiate below: ")
    val myParser = new MyParser
    roomGenerator.createAroom()
    myParser.startParsing()
  }
}
