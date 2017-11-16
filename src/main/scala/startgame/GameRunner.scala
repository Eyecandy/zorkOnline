package startgame


import command.MyParser
import organism.Player

object GameRunner {
  val player = new Player()

  def initiateEverything() = {


    println("initiate below: ")
    val myParser = new MyParser()
    println(player.getRoom.getName + ": " + player.getRoom.getStory)

    //roomGenerator.createAroom()
    myParser.startParsing()
  }


}
