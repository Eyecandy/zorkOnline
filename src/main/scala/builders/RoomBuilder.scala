package builders

import world._

import scala.collection.mutable
object RoomBuilder {
  val allRooms = mutable.HashMap[Int,Room]()

  def startingRoom():Room = {
    val r1Dir= DirectionBuilder.r1Dir
    val room1 = new Room("Location: Bottom of tower",
      "darkness covers the room, except for a torch's fire",
      r1Dir)
    allRooms.put(0,room1)
    room1
  }

  def secondRoom():Room = {
    val r1Dir= DirectionBuilder.r2Dir
    val room2 = new Room("Location: Mid-tower",
      "smokes is all over the place....",
      r1Dir)
    allRooms.put(1,room2)
    room2
  }

  def thirdRoom():Room = {
    val r1Dir= DirectionBuilder.r3Dir
    val room3 = new Room("Sewers",
      "It's smelly down here....",
      r1Dir)
    allRooms.put(2,room3)
    room3
  }
}
