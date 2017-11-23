package organism

import world.{Direction, Room}
import item._
import scala.collection.mutable
import builders.RoomBuilder

@SerialVersionUID(114L)
class Player extends Serializable {

  private var maxHP = 100
  private var maxMP = 50
  private var hp = 100
  private var mp = 50
  private var attack = 50
  private var defence = 10
  private var weaponSlot:Option[Weapon] = None
  private var armorSlot:Option[Armor] = None
  private val inventory = new mutable.HashMap[String, (Item, Int)]()
  private val spells = new mutable.HashMap[String, (Int, Int)]()

  private var room: Room = RoomBuilder.allRooms.get(0).get

  var directionChosen:Direction = room.getLocations.get("n").get

  def equip(e: Equipment)= e match {
    case w: Weapon => {
      attack = attack+w.getParameter()
      if (weaponSlot == None) weaponSlot = Some(w)
      else{
        val oldWeapon = weaponSlot.get
        inventory.put(oldWeapon.getName, (oldWeapon, 1))
      }
    }
    case a: Armor => {
      defence = defence+a.getParameter()
      if (armorSlot == None) armorSlot = Some(a)
      else{
        val oldArmor = armorSlot.get
        inventory.put(oldArmor.getName, (oldArmor, 1))
      }
    }
  }

  def recover(r: Recovery)= r match{
    case p: Potion => {
      val recoverAmount = p.getParameter()
      if (hp+recoverAmount < maxHP){
        hp = hp+recoverAmount
      }
      else hp = maxHP
    }
    case m: ManaPotion => {
      val recoverAmount = m.getParameter()
      if (mp+recoverAmount < maxMP){
        mp = mp+recoverAmount
      }
      else mp = maxMP
    }

  }

  def use(it: Item)=it match { // assuming item exist in inventory
    case e: Equipment => {
      inventory.remove(e.getName)
      equip(e)
    }
    case r: Recovery => {
      val itemQuantity = inventory(r.getName)._2
      if (itemQuantity == 1) inventory.remove(r.getName)
      else inventory.update(r.getName, (r, itemQuantity-1))
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
