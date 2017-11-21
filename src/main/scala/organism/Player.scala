package organism

import scala.collection.mutable

object Player {
  private var hp = 100
  private var mp = 50
  private var attack = 50
  private var defence = 10
//  private val inventory = new mutable.HashMap[String, (Item, Int)]()
  private val inventory = new mutable.HashMap[String, Int]() // fake one
  private val spells = new mutable.HashMap[String, (Int, Int)]()
//  private val Room = new Room()
  private val directionChosen = ""
  def getInventory()={
    inventory
  }
}
