package item
import world._
class Key(s:String, des:String,link:Link,unlockString:String) extends Item(s:String, des: String){
  val canOpen:Link = link

  def useKey(link:Link):String= {
    if (link.equals(canOpen) ) {
      link.locked = false
      link.story = unlockString
      link.name + command.CommandStrings.succesfullyUnlocked
    }
    else {
      command.CommandStrings.cantOpenThatObject
    }
  }

  override val name = s
  val description = des
  val canEquip = true
}
