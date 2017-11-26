package application

import builders.{LevelBuilder, RoomBuilder}

import command._

object LinkTest extends  App {
  LevelBuilder.createWorld()
  val player = Commands.player
  player.setDirection("e")
  player.getDirection().itemMap.foreach(x => println(x))
  //println(Commands.unlock("random"))




}
