import organism.player
import organism.player.getRoom

import scala.collection.mutable

package object commands {

  val commandMap = new mutable.HashMap[String,String => String]()
  commandMap.put("s",moveDir)
  commandMap.put("n",moveDir)
  commandMap.put("w",moveDir)
  commandMap.put("e",moveDir)
  commandMap.put("x",getRoomAndStory)
  commandMap.put("l",lookAround)


  def moveDir(input:String): String  = {
    player.setDirection(input)
    val dirName= getRoom.getLocations.get(input).get.getName
    val dirStory = getRoom.getLocations.get(input).get.getStory
    //loop through items
    val ret =  "direction: " + dirName + ": " + dirStory
    ret
  }

  def getRoomAndStory(input:String): String = {
    val roomName = getRoom.getName
    val roomStory = getRoom.getStory
    val ret = roomName + ": " + roomStory
    ret
  }

  def lookAround(input:String): String = {
    "look around"
  }

}
