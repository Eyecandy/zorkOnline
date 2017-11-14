package io.muic

import startgame.GameRunner

object main extends App{

  def startNewGame() =  {
    val game = new GameRunner()
    game.initiateEverything()
  }
  startNewGame()
}
