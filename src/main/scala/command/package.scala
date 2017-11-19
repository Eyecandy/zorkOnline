import java.io.{FileNotFoundException, IOException}

import builders.LevelBuilder.{east, west}
import builders.RoomBuilder
import memorycard.{ResourceManager, SaveData}
import startgame.GameRunner
import world.Link

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


  def useLink(input: String): String = {
    val k = player.getDirection().itemMap
    val output = player.getDirection().itemMap.get(input).getOrElse(null)
    println(output, k)
    if (output != null) {
      print(output.getName)
      val x = output.asInstanceOf[Link].teleport(player, player.getRoom)
      x
    }
    else {
      "No such thing can opened"
    }

  }

  def moveDir(input: String): String = {
    val dirName = player.getRoom.getLocations.get(input).get.getName
    val dirStory = player.getRoom.getLocations.get(input).get.getStory
    //loop through items


    player.setDirection(input)
    val x = player.getDirection().itemMap.valuesIterator.toList
    val items = x.flatMap(b => List("<br>" + b.name + ": " + b.story + "<br>"))
    if (items.isEmpty) {
      val ret = "direction: " + dirName + ": " + dirStory
      ret
    }
    else {
      val ret = "direction: " + dirName + ": " + dirStory + items
      ret

    }


  }

  def getRoomAndStory(input: String): String = {
    val roomName = player.getRoom.getName
    val roomStory = player.getRoom.getStory
    val ret = roomName + ": " + roomStory
    ret
  }

  def lookAround(input: String): String = {
    player.getDirection().getName

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
        println("file not fonund exception in saveCmd func in Commands")
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

    "loaded"
  }
}