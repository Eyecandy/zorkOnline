package world

import scala.collection.mutable

@SerialVersionUID(114L)
class Direction(nameC:String,storyC:String) extends Serializable {
  private val name = nameC
  private val story = storyC
  val itemMap = new mutable.HashMap[String,FatherOfObjects]()
  def getName: String = name
  def getStory: String = story
}
