import memorycard.{ResourceManager, SaveData}
import organism.Player
import startgame.GameRunner

import scala.collection.mutable
import scala.util.Random



package object Commands {

  var player = GameRunner.player

  val commandMap = new mutable.HashMap[String,String => String]()
  commandMap.put("s",moveDir)
  commandMap.put("n",moveDir)
  commandMap.put("w",moveDir)
  commandMap.put("e",moveDir)
  commandMap.put("x",getRoomAndStory)
  commandMap.put("l",lookAround)
  commandMap.put("save",saveCmd)
  commandMap.put("load",loadCmd)

  def moveDir(input:String): String  = {
    player.setDirection(input)
    val dirName= player.getRoom.getLocations.get(input).get.getName
    val dirStory = player.getRoom.getLocations.get(input).get.getStory
    //loop through items
    val ret =  "direction: " + dirName + ": " + dirStory
    ret
  }

  def getRoomAndStory(input:String): String = {
    val roomName = player.getRoom.getName
    val roomStory = player.getRoom.getStory
    val ret = roomName + ": " + roomStory
    ret
  }

  def lookAround(input:String): String = {
    player.getDirection().getName
  }

  def saveCmd(fileName:String): String = {
    try {
      val saveData = new SaveData()
      saveData.player = player
      println(saveData.x)
      val random = Random.nextDouble()
      saveData.x = saveData.x +  random
      ResourceManager.save(saveData,fileName)
    }
    "saved"
  }

  def loadCmd(fileName:String): String = {
    try {
      val loadedData = ResourceManager.load("save1.txt").asInstanceOf[SaveData]
      player = loadedData.player
    }

    "loaded"
  }
}