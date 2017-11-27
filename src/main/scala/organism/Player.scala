package organism

import world.{Direction, Room}
import item._
import random._
import scala.collection.mutable
import builders.RoomBuilder
@SerialVersionUID(114L)
case class ItemCount(item: Item, count: Int) extends Serializable
@SerialVersionUID(114L)
case class Spell(name:String,dmg:Int,manaCost:Int) extends Serializable

class Player extends Serializable {


  var playerLevel = 1
  var exp = 0
  var maxHP = 100
  var maxMP = 50
  var hp = 100
  var mp = 50
  var attack = 35
  var defence = 10
  var weaponSlot:Option[Weapon] = None
  var armorSlot:Option[Armor] = None

  private val inventory = new mutable.HashMap[String, ItemCount]()
  val spells = new mutable.HashMap[String, Spell]()
  spells.put("fireball", new Spell("fireball",45,30))
  spells.put("ice_lance",new Spell("fireball",45,30))
  private var room: Room = RoomBuilder.allRooms(0)
  var directionChosen:Direction = room.getLocations("n")


  def equip[T <: Equipment](e: T): Unit = {
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
                                updateSlot: T  => Unit,
                                updateParameter: Int => Unit): Unit = {
      updateParameter(e.parameter)
      if (slot.isDefined) {
        val old: T = slot.get
        inventory.put(old.getName, ItemCount(old, 1))
      }
      updateSlot(e.asInstanceOf[T])
    }
  }

  def unequip[T <: Equipment](slot: Option[T]): String={
    if (!slot.isEmpty) {
      val old: T = slot.get
      inventory.put(old.getName, ItemCount(old, 1))

      "You unequipped" + old.getName
    }
    else {"You have nothing equipped"} // print("error!! no equipment equipped in slot.")
  }

   // exp gained from each monster will be Int in range(10, 20) or (20, 40) depending on monster strength
  def levelUp():Boolean={
    val newLevel = (exp/20)+1
    val isLevelUp = (newLevel > playerLevel)
    while (newLevel > playerLevel){
      playerLevel+=1
      maxHP+=RandomNumberGenerator.RNG(10, 20)
      maxMP+=RandomNumberGenerator.RNG(10, 15)
      attack+=RandomNumberGenerator.RNG(2, 7)
      defence+=RandomNumberGenerator.RNG(2, 7)
    }
    isLevelUp
  }

  def castSpell(spell_monster:String): String = {
    val lstSpMo = spell_monster.split("-")
    val spellS = lstSpMo.head
    val monsterS = lstSpMo.tail.head
    if (spells.getOrElse(spellS,null) == null || getDirection().itemMap.getOrElse(monsterS,null) == null ) {
      "Either you don't have that spell or that creature doesn't exist"
    }
    else {

      val spellToCast:Spell = spells(spellS)
      if (mp < spellToCast.manaCost) {
        "Not enough mana"
      }
      else {

        val isMonster = getDirection().itemMap(monsterS).isInstanceOf[Monster]
        isMonster match {
          case false => "That's not a monster"
          case true => {

            val monster = getDirection().itemMap(monsterS).asInstanceOf[Monster]
            if (monster.hp < 0) {monster.name + " is already dead"}
            else {
              mp = mp - spellToCast.manaCost
              monster.hp = monster.hp - spellToCast.dmg
              monster.name + " takes " + spellToCast.dmg+ " spell damage <br>" + monster.dead()
            }

          }
        }
      }


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


  def attack(monsterName:String):String = {
    val fob = getDirection().itemMap.getOrElse(monsterName,null)
    fob match {
      case null => "No such thing to attack here"
      case _ => {
        if (fob.isInstanceOf[Monster]) {
          val monster:Monster =  fob.asInstanceOf[Monster]
          if (monster.hp < 0) {monster.name + " is already dead"}
          else {
            monster.hp = monster.hp + (monster.defence - attack)
            monster.name + " attacked for " + math.abs(( - attack + defence) )+ " damage <br>" + monster.dead()
          }
        }
        else {
          "that is not a monster"
        }
      }
    }
  }

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
      val itemCount = inventory.getOrElse(e.getName,null)
      if (itemCount == null) {
        "No such item in your inventory"
      }
      else {
        inventory.remove(e.getName)
        val equi = equip(e)
        "you equipped " + e.name
      }
    }
    case r: Recovery => {
      val itemCount = inventory.getOrElse(r.getName,null)
      if (itemCount == null) {"No such item in your inventory"}
      else {
        val itemQuantity = itemCount.count
        if (itemQuantity == 1) inventory.remove(r.getName)
        else inventory.update(r.getName, ItemCount(r, itemQuantity-1))
        val hpRecString =  math.min(maxHP - hp,r.parameter)
        val recAmount = recover(r)

        "<br> You recovered for " + hpRecString + "hp"
      }
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
