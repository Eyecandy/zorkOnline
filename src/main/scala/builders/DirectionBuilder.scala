package builders

import item.Weapon
import world.{Direction, FatherOfObjects, Link}

import scala.collection.mutable

object DirectionBuilder {
  private val south= "South"
  private val north= "North"
  private val east = "East"
  private val west = "West"


  def r1Dir = {
    val r1ls = new Direction(south,"a wind strong wind is coming from above");
    val r1le = new Direction(east,"you are near the torch");
    val r1lw = new Direction(west,"staircases lead up in a spiral");
    val r1ln = new Direction(north,"a barricaded door is in front of you");
    val r1Dir= new mutable.HashMap[String,Direction]()
    val sword = new Weapon("sword","it'is shiny",10);
    r1ln.itemMap.put(sword.getName,sword);

    r1Dir.put("s",r1ls);r1Dir.put("w",r1lw);r1Dir.put("e",r1le);r1Dir.put("n",r1ln);
    r1Dir
  }

  def r2Dir = {

    val r1ls = new Direction(south,"blue");
    val r1le = new Direction(east,"red");
    val r1lw = new Direction(west,"yellow");
    val r1ln = new Direction(north,"orange");
    val r1Dir= new mutable.HashMap[String,Direction]()

    r1Dir.put("s",r1ls);r1Dir.put("w",r1lw);r1Dir.put("e",r1le);r1Dir.put("n",r1ln);
    r1Dir
  }
}
