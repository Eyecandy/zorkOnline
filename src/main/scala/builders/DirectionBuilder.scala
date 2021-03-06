package builders

import item.{Armor, ManaPotion, Potion, Weapon}
import organism.Monster
import world.{Direction, FatherOfObjects, Link}

import scala.collection.mutable

object DirectionBuilder {

  def r1Dir = {
    val r1ls = new Direction(DirStrings.southS,"a wind strong wind is coming from above");
    val r1le = new Direction(DirStrings.eastS,"A fire place is here...with burning wood...");
    val r1lw = new Direction(DirStrings.westS,"dark wall ");
    val r1ln = new Direction(DirStrings.northS,"white wall with a nice painting on it");
    val r1Dir= new mutable.HashMap[String,Direction]()
    val sword = new Weapon("sword","it'is shiny",10);
    val wolfy = new Monster("wolfy","roaring,black fur fangs and stuff",false);
    val manapotion = new ManaPotion("mpp","minor mana potion",25);
    val hpPot = new Potion("hp_pot", "it's in an old vial",30)
    r1ls.itemMap.put(manapotion.name,manapotion)
    r1ls.itemMap.put(wolfy.name,wolfy)
    r1ln.itemMap.put(sword.getName,sword)
    r1ln.itemMap.put(hpPot.name, hpPot)

    r1Dir.put("s",r1ls);r1Dir.put("w",r1lw);r1Dir.put("e",r1le);r1Dir.put("n",r1ln);
    r1Dir
  }

  def r2Dir = {
    val r1ls = new Direction(DirStrings.southS,"blue");
    val r1le = new Direction(DirStrings.eastS,"red");
    val r1lw = new Direction(DirStrings.westS,"yellow");
    val r1ln = new Direction(DirStrings.northS,"orange");
    val hammer = new Weapon("hammer","it's not so shiny",10);
    val manapotion = new ManaPotion("mp_pot","minor mana potion",25);
    r1le.itemMap.put(manapotion.name, manapotion)

    r1ls.itemMap.put(hammer.getName,hammer)
    val r1Dir= new mutable.HashMap[String,Direction]()
    r1Dir.put("s",r1ls);r1Dir.put("w",r1lw);r1Dir.put("e",r1le);r1Dir.put("n",r1ln);
    r1Dir
  }


  def r3Dir = {
    val r1ls = new Direction(DirStrings.southS,"a weird sound echoes");
    val r1le = new Direction(DirStrings.eastS,"The floor is supah cool");
    val r1lw = new Direction(DirStrings.westS,"a good story here..");
    val r1ln = new Direction(DirStrings.northS,"some other random story");
    val axe = new Weapon("axe","it's sharp and blunt at the same time",20);
    val armor = new Armor("helmet","an old mail helmet",7);
    val rat = new Monster("rat","red eyes and annoying..",true);
    r1ln.itemMap.put(rat.name,rat)
    r1ls.itemMap.put(armor.name,armor)
    r1ls.itemMap.put(axe.getName,axe)
    val r1Dir= new mutable.HashMap[String,Direction]()
    r1Dir.put("s",r1ls);r1Dir.put("w",r1lw);r1Dir.put("e",r1le);r1Dir.put("n",r1ln);
    r1Dir
  }
}
