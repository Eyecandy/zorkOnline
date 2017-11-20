package world


@SerialVersionUID(114L)
abstract class FatherOfObjects extends Serializable {
  val name:String
  var story:String

  def getName = name
  def getStory = story
  def canEquip:Boolean

}
