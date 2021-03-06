package world
import organism.Player


class Link(lockedC:Boolean,room1C:Room,room2C:Room,nameC:String,storyC:String) extends FatherOfObjects {
  var locked = lockedC
  override val name = nameC
  override var story = storyC
  val room1 = room1C
  val room2 = room2C
  override val pickable = false
  override def isEquip = false

  val lockedText = "door appears to be opoen"

  def teleport(player:Player, currentRoom: Room): String = {
    locked match {
      case true => "That door is locked"
      case false =>  {
        if (player.getRoom.getName.equals(room1.getName)) {
          player.setRoom(room2)
        }
        else {
          player.setRoom(room1)
        }
        val ret = "You enter a new Room <br>" + player.getRoom.getName + ":" + player.getRoom.getStory
        ret
      }
    }

  }

}
