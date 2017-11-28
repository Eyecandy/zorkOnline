package command

import java.io.{FileNotFoundException, IOException}

import builders.LevelBuilder.{east, west}
import builders.RoomBuilder
import item.{Equipment, Item, Key}
import memorycard.{ResourceManager, SaveData}
import startgame.GameRunner
import organism._
import world.{FatherOfObjects, Link}

import scala.collection.mutable
import scala.util.Random


object Commands {

  var player = GameRunner.player
  val commandMap = mutable.HashMap[String, String => String]()
  commandMap.put("s", moveDir)
  commandMap.put("n", moveDir)
  commandMap.put("w", moveDir)
  commandMap.put("e", moveDir)
  commandMap.put("x", getRoomAndStory)
  commandMap.put("l", lookAround)
  commandMap.put("save", saveCmd)
  commandMap.put("load", loadCmd)
  commandMap.put("open", useLink)
  commandMap.put("take",pickup)
  commandMap.put("inventory",checkInventory)
  commandMap.put("equip",equip)
  commandMap.put("throw",throwItem)
  commandMap.put("stats",getPlayerStats)
  commandMap.put("unlock",unlock)
  commandMap.put("attack",attack)
  commandMap.put("use",use)
  commandMap.put("cast",castSpell)
  commandMap.put("unequip",unequip)

  def unequip(slot:String):String = {
    if (slot.equals("weapon")) {
      val res = player.unequip(player.weaponSlot)
      player.weaponSlot = None
      res

    }
    else if (slot.equals("armor")) {

     val res = player.unequip(player.armorSlot)
      player.armorSlot = None
      res
    }
    else {
      CommandStrings.noSuchSlot
    }
  }

  def castSpell(spell_monster:String): String= {
    player.castSpell(spell_monster)
  }

  def use(input:String):String = {
    val itemCount = player.getInventory.getOrElse(input,null)
    itemCount match  {
      case null => CommandStrings.noSuchItemInventory
      case _ => {
        player.use(itemCount.item)
      }
    }
  }

  def attack(monsterName:String): String = {
    player.attack(monsterName)
  }

  def throwItem(input:String): String = {
    val itemToThrow: ItemCount = player.getInventory.getOrElse(input,null)
    itemToThrow match {
      case null => CommandStrings.noSuchItemInventory
      case _ => {
        val currDirItemMap = player.getDirection().itemMap
        val item = itemToThrow.item
        currDirItemMap.put(item.name,item)
        player.getInventory.remove(item.name)
        "threw away " + item.name
      }
    }
  }

  def getPlayerStats(input:String): String = {

    var stats = "Dmg: " + player.attack + " max hp: " + player.maxHP + " max mana: " + player.maxMP + " curr hp: " +
      player.hp + " curr Mana: " + player.mp  + " def: " + player.defence

    if (player.armorSlot.isDefined) {
      stats += " armor equipped " + player.armorSlot.get.name
    }
    if (player.weaponSlot.isDefined) {
      stats += " weapon equipped " + player.weaponSlot.get.name
    }
    stats
  }


  def equip(input: String): String = {
    val itemCount= player.getInventory.getOrElse(input,null)

    itemCount match{
      case null => CommandStrings.noSuchItemInventory
      case _ => {
        player.use(itemCount.item)
        "equipped " + itemCount.item.name
      }
    }
  }

  def pickup(input:String):String = {
    val fatherOB: FatherOfObjects = player.getDirection().itemMap.getOrElse(input, null)
    if (fatherOB != null && fatherOB.pickable) {
      val item = fatherOB.asInstanceOf[Item]
      val inventory = player.getInventory

      inventory.contains(item.name) match {
        case false => inventory.put(item.name, ItemCount(item, 1))
        case true => {
          val invitem = inventory.get(item.name)
          val incrQuantityItem = ItemCount(item, invitem.get.count + 1)
          inventory.put(item.name, incrQuantityItem)
        }
      }
      player.getDirection().itemMap.remove(item.name)
      "You picked up " + item.name
    }
    else {
      "Either the entity requested can't be picked up or it could be that it does not exist"
    }

  }


  def checkInventory(inputNotUsed:String) = {
    player.getInventory.isEmpty match {
      case true => CommandStrings.emptyInventory
      case false => player.getInventory.valuesIterator.foldLeft("")( (acc,item) => acc + item.item.name +" quantity: "+ item.count + "<br>")
    }
  }

  def useLink(input: String): String = {
    val k = player.getDirection().itemMap
    val output: FatherOfObjects = player.getDirection().itemMap.getOrElse(input, null)
    output match {
      case null => CommandStrings.cantOpenThatObject
      case _  => output.asInstanceOf[Link].teleport(player, player.getRoom)
    }
  }

  def moveDir(input: String): String = {
    player.setDirection(input)
    val dirName = player.getDirection().getName
    val dirStory = player.getDirection().getStory
    val allFOatDir = player.getDirection().itemMap.valuesIterator
    val items = allFOatDir.foldLeft("")((acc, item) => acc + "<br>" + item.name + ": " + item.story )
    items.isEmpty match {
      case true => "direction: " + dirName + ": " + dirStory
      case false => "direction: " + dirName + ": " + dirStory + items
    }
  }

  def getRoomAndStory(input: String): String = {
    val roomName = player.getRoom.getName
    val roomStory = player.getRoom.getStory
    val ret = roomName + ": " + roomStory + "<br>" + player.getDirection().getName + ": "+player.getDirection().getStory
    ret
  }

  def lookAround(input: String): String = {
    val roomDirs = player.getRoom.getLocations
    val dirsString = roomDirs.foldLeft("")((acc,elt) => acc +  "<br>"+ elt._1 +": " +elt._2.getStory + elt._2.itemMap.
      foldLeft("")((acc,elt) => acc + "<br>" +elt._2.name + ": "+elt._2.story))

    val dir  = player.getDirection()

    dirsString
  }

  def saveCmd(fileName: String): String = {
    try {
      val saveData = new SaveData()
      saveData.player = player
      println(saveData.x)
      val random = Random.nextDouble()
      saveData.x = saveData.x + random
      ResourceManager.save(saveData, fileName)
    }
    catch {
      case ex: FileNotFoundException => {
        println("file not found exception in saveCmd func in Commands")
      }
      case ex: IOException => {
        println("IO Exception")
      }
    }
    "saved"
  }

  def loadCmd(fileName: String): String = {
    try {
      val loadedData = ResourceManager.load("save1.txt").asInstanceOf[SaveData]
      player = loadedData.player
    }
    catch {
      case ex:FileNotFoundException => {
        println("file not found exception in commands object")
      }

      case ex: IOException => {
        println("IO Exception")
      }
    }
    "loaded" + "<br>" + getRoomAndStory("Whatever string")
  }

  def unlock(key_link:String): String = {
    val key_linkA = key_link.split("-")
    val link = key_linkA.head
    val key = key_linkA.tail.head
    val itemKey: ItemCount = player.getInventory.getOrElse(key,null)
    itemKey match {
      case null => CommandStrings.noSuchItemInventory
      case _ =>  {
        if (itemKey.item.isInstanceOf[Key]) {
          linkUnlockingAttempt(link,itemKey.item.asInstanceOf[Key])
        }
        else {
          CommandStrings.notValidKey
        }
      }
    }
  }

  def linkUnlockingAttempt(link:String,key:Key): String = {
    val realLink = player.getDirection().itemMap.getOrElse(link,null)
    realLink match{
      case null =>  CommandStrings.notValidLink
      case _ => {
        if (realLink.isInstanceOf[Link]) {
          key.useKey(realLink.asInstanceOf[Link])
        }
        else {
          CommandStrings.notAlinkUnlock
        }
      }
    }
  }
}