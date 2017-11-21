package organism

import world.{Direction, Room}

import scala.collection.mutable
import builders.RoomBuilder

@SerialVersionUID(114L)
class Player extends Serializable {

  private var hp = 100
  private var mp = 50
  private var attack = 50
  private var defence = 10
//  private val inventory = new mutable.HashMap[String, (Item, Int)]()
  private val inventory = new mutable.HashMap[String, Int]() // fake one
  private val spells = new mutable.HashMap[String, (Int, Int)]()

  private var room: Room = RoomBuilder.allRooms.get(0).get

  var directionChosen:Direction = room.getLocations.get("n").get


  def getRoom: Room = room

  def getInventory = inventory

  def setRoom(room:Room) = {this.room = room}

  def setDirection(dir:String): Unit =    {
    directionChosen = room.getLocations.get(dir).get
  }
  def getDirection(): Direction = {
    directionChosen
  }

}
