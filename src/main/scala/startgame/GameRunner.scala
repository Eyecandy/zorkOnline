package startgame


import command.MyParser

class GameRunner {
  def initiateEverything() = {
    println("initiate below: ")
    val myParser = new MyParser
    myParser.startParsing()
  }
}
