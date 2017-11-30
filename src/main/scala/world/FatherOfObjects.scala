package world


@SerialVersionUID(114L)
abstract class FatherOfObjects extends Serializable {
  val name:String
  var story:String
  val pickable:Boolean
  def isEquip:Boolean
  def getName = name
  def getStory = story

}
