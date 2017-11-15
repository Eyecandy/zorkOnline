package startgame


import command.MyParser
import organism.player

class GameRunner {
  def initiateEverything() = {
    println("initiate below: ")
    val myParser = new MyParser
    println(player.getRoom.getName + ": " + player.getRoom.getStory)

    //roomGenerator.createAroom()
    myParser.startParsing()
  }
}
