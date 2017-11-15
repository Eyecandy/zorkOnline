import scala.collection.mutable

package object commands {

  val commandMap = new mutable.HashMap[String,String => String]()
  commandMap.put("s",moveSouth)
  commandMap.put("n",moveNorth)
  commandMap.put("w",moveWest)
  commandMap.put("e",moveEast)


  def moveSouth(input:String): String = {
    "go south"
  }

  def moveNorth(input:String): String = {
    "go north"
  }

  def moveEast(input:String): String = {
    "go East"
  }

  def moveWest(input:String): String = {
    "go West"
  }

}
