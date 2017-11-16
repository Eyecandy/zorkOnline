package io.muic

import startgame.GameRunner

import factories._

object main extends App{

  def startNewGame() =  {
    val game = new GameRunner()
    game.initiateEverything()
    //roomGenerator.createAroom()
  }
  startNewGame()
}