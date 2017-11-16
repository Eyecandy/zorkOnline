package startgame


import command.MyParser
import organism.Player

class GameRunner {
  def initiateEverything() = {
    println("initiate below: ")
    val myParser = new MyParser
    println(Player.getRoom.getName + ": " + Player.getRoom.getStory)

    //roomGenerator.createAroom()
    myParser.startParsing()
  }
}
