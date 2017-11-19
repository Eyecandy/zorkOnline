package world
import organism.Player


class Link(lockedC:Boolean,room1C:Room,room2C:Room,nameC:String,storyC:String) extends FatherOfObjects {
  var locked = lockedC
  override val name = nameC
  override var story = storyC
  val room1 = room1C
  val room2 = room2C
  override def canEquip = false

  def teleport(player:Player, currentRoom: Room): String = {

    if (player.getRoom.getName.equals(room1.getName)) {
      player.setRoom(room2)
      println("changed to room2")
    }
    else {
      player.setRoom(room1)
      println("changed to room1")

    }
    val ret = "You enter a new Room <br>" + player.getRoom.getName + ":" + player.getRoom.getStory
    ret

  }
}
