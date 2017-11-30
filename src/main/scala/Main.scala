package io.muic

import startgame.GameRunner

import builders._

object Main extends App{

  def startNewGame() =  {
    GameRunner.initiateEverything()
  }
  startNewGame()
}