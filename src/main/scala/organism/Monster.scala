package organism

import world.FatherOfObjects
import Commands.player

class Monster(nameC: String,storyC:String) extends  FatherOfObjects{
 // private val monsterName = name
  var hp = 100
  var mp = 50
  var attack = 15
  var defence = 10
  //private val itemDrop = String // Item
  override val name = nameC
  override var story = storyC
  override val pickable = false


  override def isEquip = false

  def dead() = {
    if (hp < 0) {
      story = " lying dead on the floor "
      "<br>"+name + " died..."
    }
    else {
      player.hp = player.hp + (player.defence - attack)
      name + " retaliates for " + attack + " damage"
    }

  }


}
