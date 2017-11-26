package organism

import world.{Direction, Room}
import item._
import random._
import scala.collection.mutable
import builders.RoomBuilder

@SerialVersionUID(114L)
case class ItemCount(item: Item, count: Int)
class Player extends Serializable {

  private var playerLevel = 1
  private var exp = 0
  private var maxHP = 100
  private var maxMP = 50
  private var hp = 100
  private var mp = 50
  private var attack = 50
  private var defence = 10
  private var weaponSlot:Option[Weapon] = None
  private var armorSlot:Option[Armor] = None
  private val inventory = new mutable.HashMap[String, ItemCount]()
  private val spells = new mutable.HashMap[String, (Int, Int)]()

  private var room: Room = RoomBuilder.allRooms.get(0).get

  var directionChosen:Direction = room.getLocations.get("n").get


  def equip(e: Equipment)= {
    e match {
      case _: Weapon =>
        update(weaponSlot,
          (t: Weapon) => weaponSlot = Some(t),
          (inc: Int) => attack += inc)
      case _: Armor =>
        update(armorSlot,
          (t: Armor) => armorSlot = Some(t),
          (inc: Int) => defence += inc)
    }


    def update[T <: Equipment] (slot: Option[T],
                                updateSlot: T => Unit,
                                updateParameter: Int => Unit): Unit = {
      updateParameter(e.parameter)

      if (!slot.isEmpty) {
        val old: T = slot.get
        inventory.put(old.getName, ItemCount(old, 1))
      }
      updateSlot(e)
    }
  }

  def unequip[T <: Equipment](slot: Option[T])={
    if (!slot.isEmpty) {
      val old: T = slot.get
      inventory.put(old.getName, ItemCount(old, 1))
    }
    else {} // print("error!! no equipment equipped in slot.")
  }

   // exp gained from each monster will be Int in range(10, 20) or (20, 40) depending on monster strength
  def levelUp(newLevel: Int):Unit={
//    val newLevel = (exp/20)+1
    if (newLevel > playerLevel){
      playerLevel+=1
      maxHP+=RandomNumberGenerator.RNG(10, 20)
      maxMP+=RandomNumberGenerator.RNG(10, 15)
      attack+=RandomNumberGenerator.RNG(2, 7)
      defence+=RandomNumberGenerator.RNG(2, 7)
      levelUp(newLevel)
    }
  }


//    e match {
//    case w: Weapon => {
//      attack = attack+w.parameter
//      if (weaponSlot == None) weaponSlot = Some(w)
//      else{
//        val oldWeapon = weaponSlot.get
//        inventory.put(oldWeapon.getName, (oldWeapon, 1))
//      }
//    }
//    case a: Armor => {
//      defence = defence+a.parameter
//      if (armorSlot == None) armorSlot = Some(a)
//      else{
//        val oldArmor = armorSlot.get
//        inventory.put(oldArmor.getName, (oldArmor, 1))
//      }
//    }
//  }

  def recover(r: Recovery)= {
    val recoverAmount = r.parameter
    r match {
      case _: Potion => {
        hp = math.min(maxHP, hp + recoverAmount)
      }
      case _: ManaPotion => {
        mp = math.min(maxMP, mp + recoverAmount)
      }
    }
  }


  def use(it: Item)=it match { // assuming item exist in inventory
    case e: Equipment => {
      inventory.remove(e.getName)
      equip(e)
    }
    case r: Recovery => {
      val itemQuantity = inventory(r.getName).count
      if (itemQuantity == 1) inventory.remove(r.getName)
      else inventory.update(r.getName, ItemCount(r, itemQuantity-1))
      recover(r)
    }
  }



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
