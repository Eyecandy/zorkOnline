package builders

import item.{Potion, Weapon}
import organism.Monster
import world.{Direction, FatherOfObjects, Link}

import scala.collection.mutable

object DirectionBuilder {
  private val south= "South"
  private val north= "North"
  private val east = "East"
  private val west = "West"


  def r1Dir = {
    val r1ls = new Direction(south,"a wind strong wind is coming from above");
    val r1le = new Direction(east,"A fire place is here lightning up the room");
    val r1lw = new Direction(west,"dark wall ");
    val r1ln = new Direction(north,"white wall with a nice painting on it");
    val r1Dir= new mutable.HashMap[String,Direction]()
    val sword = new Weapon("sword","it'is shiny",10);
    val wolfy = new Monster("wolfy","roaring,black fur fangs and stuff");
    val hpPot = new Potion("hp_pot", "it's in an old vial",30)
    r1ls.itemMap.put(wolfy.name,wolfy)
    r1ln.itemMap.put(sword.getName,sword)
    r1ln.itemMap.put(hpPot.name, hpPot)
    r1Dir.put("s",r1ls);r1Dir.put("w",r1lw);r1Dir.put("e",r1le);r1Dir.put("n",r1ln);
    r1Dir
  }

  def r2Dir = {

    val r1ls = new Direction(south,"blue");
    val r1le = new Direction(east,"red");
    val r1lw = new Direction(west,"yellow");
    val r1ln = new Direction(north,"orange");
    val hammer = new Weapon("hammer","it's not so shiny",10);
    r1ls.itemMap.put(hammer.getName,hammer)
    val r1Dir= new mutable.HashMap[String,Direction]()

    r1Dir.put("s",r1ls);r1Dir.put("w",r1lw);r1Dir.put("e",r1le);r1Dir.put("n",r1ln);
    r1Dir
  }
}
