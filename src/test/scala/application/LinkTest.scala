package application

import builders.{LevelBuilder, RoomBuilder}
import command._
import item.Key
import organism.ItemCount
import world.Link

object LinkTest extends  App {
  LevelBuilder.createWorld()
  val player = Commands.player
  player.setDirection("e")
  player.getDirection().itemMap.foreach(x => println(x))
  val linkATloc = player.getDirection().itemMap.getOrElse("old_door",null).asInstanceOf[Link]
  assert(linkATloc.isInstanceOf[Link])
  assert(linkATloc.locked == true)
  assert(command.Commands.unlock("brass_key-old_door").equals(CommandStrings.noSuchItemInventory))
  val key:Key = new Key("brass_key","it's rusty",linkATloc,"it's open")
  player.getInventory.put(key.name,new ItemCount(key,1))
  assert(command.Commands.unlock("old_door-brass_key").equals("old_door successfully unlocked"))
  player.setDirection("w")
  val link  = player.getDirection().itemMap("latch").asInstanceOf[Link]
  println(link.teleport(player,player.getRoom))



}
