package organism

import world.{Direction, Room}

import scala.collection.mutable
import factories.roomGenerator

object player {
  private var hp = 100
  private var mp = 50
//  private val inventory = new mutable.HashMap[String, (Item, Int)]()
  private val inventory = new mutable.HashMap[String, Int]() // fake one
  private val spells = new mutable.HashMap[String, (Int, Int)]()

  private[this] var room: Room = roomGenerator.createAroom()

  private var directionChosen:Direction = room.getLocations.get("n").get

  def getRoom: Room = room

  def getInventory = inventory

  def setRoom(room:Room) = this.room = room

  def setDirection(dir:String): Unit =    {
    directionChosen = room.getLocations.get(dir).get
  }
  def getDirection(): Direction = {
    directionChosen
  }





}
