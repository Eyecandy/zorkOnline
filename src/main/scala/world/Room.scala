package world

import scala.collection.mutable

class Room(nameC:String,storyC:String,locationsC:mutable.HashMap[String,Direction]) {
  private val name = nameC
  private val story = storyC
  private val locations = locationsC

  def getName: String = name

  def getStory: String = story

  def getLocations: mutable.HashMap[String,Direction] = locations

  //def setLocations(locations:mutable.HashMap[String,String => String]) = this.locations = locations

}
