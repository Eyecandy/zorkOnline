package factories

import world._

import scala.collection.mutable

object roomGenerator {
  private val south= "South"
  private val north= "North"
  private val east = "East"
  private val west = "West"

  def createAroom():Room = {
    val r1ls = new Direction(south,"cool");
    val r1le = new Direction(east,"yolo");
    val r1lw = new Direction(west,"heyhey");
    val r1ln = new Direction(north,"snowy");

    val r1Dir= new mutable.HashMap[String,Direction]()
    r1Dir.put("s",r1ls);r1Dir.put("w",r1lw);r1Dir.put("e",r1le);r1Dir.put("n",r1ln);

    val room1 = new Room("ancient","coolStoryBruh",r1Dir)
    println(room1.getName, room1.getStory)
    val roomDir = room1.getLocations
    roomDir.keys.foreach( key => println(roomDir.get(key).get.getName, roomDir.get(key).get.getStory ))
    room1
  }

}
