package startgame


import command.MyParser
import organism.Player

object GameRunner {
  val player = new Player()

  def initiateEverything() = {
    MyParser.startParsing()
    println(player.getRoom.getName + ": " + player.getRoom.getStory)
  }


}
