package world

@SerialVersionUID(114L)
class Direction(nameC:String,storyC:String) extends Serializable {
  private val name = nameC
  private val story = storyC

//  private val itemMap = ???

  def getName: String = name

  def getStory: String = story

}
