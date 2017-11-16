package world

import scala.collection.mutable

class Room(nameC:String,storyC:String,directionsC:mutable.HashMap[String,Direction]) {
  private val name = nameC
  private val story = storyC
  private val directions = directionsC

  def getName: String = name

  def getStory: String = story

  def getLocations: mutable.HashMap[String,Direction] = directions

  //def setLocations(locations:mutable.HashMap[String,String => String]) = this.locations = locations

}
