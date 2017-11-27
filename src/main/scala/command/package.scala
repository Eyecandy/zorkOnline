import java.io.{FileNotFoundException, IOException}

import builders.LevelBuilder.{east, west}
import builders.RoomBuilder
import item.{Equipment, Item}
import memorycard.{ResourceManager, SaveData}
import startgame.GameRunner
import organism._
import world.{FatherOfObjects, Link}

import scala.collection.mutable
import scala.util.Random


package object Commands {

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
  commandMap.put("pick",pickup)
  commandMap.put("inventory",checkInventory)
  commandMap.put("equip",equip)



  def equip(input: String): String = {
    val itemCount= player.getInventory.getOrElse(input,null)

    itemCount match{
      case null => "No such item in inventory"
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
      "You picked up" + item.name
    }
    else {
      "None"
    }

  }




  def checkInventory(inputNotUsed:String) = {
    player.getInventory.isEmpty match {
      case true => "Your invetory is empty"
      case false => player.getInventory.valuesIterator.foldLeft("")( (acc,item) => acc + item.item.name +" quantity: "+ item.count + "<br>")
    }
  }

  def useLink(input: String): String = {
    val k = player.getDirection().itemMap
    val output: FatherOfObjects = player.getDirection().itemMap.getOrElse(input, null)
    output match {
      case null => "No such thing can opened"
      case _  => output.asInstanceOf[Link].teleport(player, player.getRoom)
    }
  }

  def moveDir(input: String): String = {
    player.setDirection(input)
    val dirName = player.getDirection().getName
    val dirStory = player.getDirection().getStory
    val allFOatDir = player.getDirection().itemMap.valuesIterator
    val items = allFOatDir.foldLeft("")((acc, item) => acc + "<br>" + item.name + ": " + item.story + "<br>")
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
    val dir  = player.getDirection()
    dir.getName + ": " + dir.getStory
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
}