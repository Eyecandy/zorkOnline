package builders

import builders.RoomBuilder.{allRooms, secondRoom, startingRoom}
import item.Key
import world.Link

import DirStrings._

object LevelBuilder {
  private val east = "e"
  private val north = "n"
  private val south = "s"
  private val west = "w"
  def createWorld(): Unit = {
    startingRoom()
    secondRoom()

    val room0 = allRooms.get(0).get
    val room1 = allRooms.get(1).get
    val link0= new Link(true,room0,room1,"old_door",
      "it appears to be locked")
    val key:Key = new Key("brass_key","it's rusty",link0,"it's open")
    room0.getLocations(south).itemMap.put(key.name,key)
    room0.getLocations.get(DirStrings.east).get.itemMap.put(link0.name,link0)
    room1.getLocations.get(DirStrings.west).get.itemMap.put(link0.name,link0)

  }

}
