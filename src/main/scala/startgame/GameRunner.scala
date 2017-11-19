package startgame


import builders.LevelBuilder
import command.MyParser
import organism.Player

object GameRunner {
  LevelBuilder.createWorld()
  val player = new Player()

  def initiateEverything() = {

    MyParser.startParsing()
    println(player.getRoom.getName + ": " + player.getRoom.getStory)
  }



}
