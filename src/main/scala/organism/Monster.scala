package organism

import world.FatherOfObjects
import command.Commands._
import random.RandomNumberGenerator._
import startgame.GameRunner

class Monster(nameC: String,storyC:String, isStrong: Boolean) extends  FatherOfObjects{
  val stats = monsterStatsMap(isStrong)
  var hp = RNG(stats.hp._1, stats.hp._2)
  var attack = RNG(stats.atk._1, stats.atk._2)
  var defence = RNG(stats.dff._1, stats.dff._2)
  val exp = RNG(stats.exp._1, stats.exp._2)
  //private val itemDrop = String // Item
  override val name = nameC
  override var story = storyC
  override val pickable = false


  override def isEquip = false

  def dead():String = {
    if (hp < 0) {
      player.exp+=exp
      val isLevelUp = player.levelUp()
      if (isLevelUp){
        story = " lying dead on the floor "
        "<br>"+name + " died... " +
          "<br> You have leveled up to lv. "+player.playerLevel+" !!"+
          "<br> Your new stats are: " + command.Commands.getPlayerStats("stats")
      }
      else{
        story = " lying dead on the floor "
        "<br>"+name + " died..."
      }

    }
    else {
      player.hp = player.hp + (player.defence - attack)
      val res =name + " retaliates for " + (attack - player.defence) + " damage"  +"("+ player.defence+" blocked)"
      if (player.hp < 0) {
        hp =  RNG(stats.hp._1, stats.hp._2)
        attack = RNG(stats.atk._1, stats.atk._2)
        player.hp = player.maxHP
        val finalRes = res+ "<br> you died, you are set back to full hp and so is  "+ name
        finalRes
      }
      else {
        res
      }

    }

  }

}
