package application

import builders.{LevelBuilder, RoomBuilder}
import organism.Player

object DirectionTest  extends  App {
  LevelBuilder.createWorld()
  val player = new Player()
  val lst = RoomBuilder.allRooms.get(0).get.getLocations.flatMap(x => List(x))
  val lst2 =RoomBuilder.allRooms.get(1).get.getLocations.flatMap(x => List(x))
  assert(lst.size == 4 && lst2.size == 4)
  assert(RoomBuilder.allRooms.get(0).get.hashCode() == player.getRoom.hashCode())
  assert(player.getDirection().getName.eq("North"))
  assert(player.getInventory.size == 0)
  player.setDirection("e")
  assert(player.getDirection().getName.equals("East"))
  player.setDirection("s")
  assert(player.getDirection().getName.equals("South"))
  assert(player.getDirection().itemMap.hashCode == RoomBuilder.allRooms(0).getLocations.get("s").get.itemMap.hashCode())
  player.setDirection("w")
  assert(player.getDirection().getName.equals("West"))
  assert(player.getDirection().itemMap.hashCode == RoomBuilder.allRooms(0).getLocations.get("w").get.itemMap.hashCode())



}
