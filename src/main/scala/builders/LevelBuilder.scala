package builders

import builders.RoomBuilder.{allRooms, secondRoom, startingRoom}
import world.Link

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
    val link0= new Link(false,room0,room1,"old_door",
      "it appears to be open")
    room0.getLocations.get(east).get.itemMap.put(link0.name,link0)
    room1.getLocations.get(west).get.itemMap.put(link0.name,link0)

  }

}
