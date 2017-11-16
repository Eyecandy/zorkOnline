package builders

import world._

import scala.collection.mutable

object RoomBuilder {
  private val south= "South"
  private val north= "North"
  private val east = "East"
  private val west = "West"

  def createAroom():Room = {
    val r1ls = new Direction(south,"a wind strong wind is coming from above");
    val r1le = new Direction(east,"you are near the torch");
    val r1lw = new Direction(west,"staircases lead up in a spiral");
    val r1ln = new Direction(north,"a barricaded door is in front of you");

    val r1Dir= new mutable.HashMap[String,Direction]()
    r1Dir.put("s",r1ls);r1Dir.put("w",r1lw);r1Dir.put("e",r1le);r1Dir.put("n",r1ln);

    val room1 = new Room("Location: Bottom of tower","darkness covers the room, except for a torch's fire",r1Dir)
   // println(room1.getName, room1.getStory)
    val roomDir = room1.getLocations
    
    room1
  }

}
