package builders

import builders.RoomBuilder.{allRooms, secondRoom, startingRoom}
import item.Key
import world.{Link, Room}
import DirStrings._

object LevelBuilder {
  private val east = "e"
  private val north = "n"
  private val south = "s"
  private val west = "w"
  def createWorld(): Unit = {
    RoomBuilder.startingRoom()
    RoomBuilder.secondRoom()
    RoomBuilder.thirdRoom()

    val room0 = allRooms(0)
    val room1 = allRooms(1)
    val link0= new Link(true,room0,room1,"old_door",
      "it appears to be locked")
    val key:Key = new Key("brass_key","it's rusty",link0,"it's open")
    linking(room0,room1,link0,DirStrings.east,DirStrings.west)
    room0.getLocations(DirStrings.south).itemMap.put(key.name,key)

    val room2  = allRooms(2)
    val link1 = new Link(false,room0,room2,"latch",
      "it appears to be open")

    room2.getLocations(DirStrings.east).itemMap.put(link1.name,link0)
    linking(room0,room2,link1,DirStrings.west,DirStrings.east)
  }


  private def linking(room0:Room,room1:Room,link:Link,d1:String,d2:String): Unit = {
    room0.getLocations.get(d1).get.itemMap.put(link.name,link)
    room1.getLocations(d2).itemMap.put(link.name,link)
  }

}
