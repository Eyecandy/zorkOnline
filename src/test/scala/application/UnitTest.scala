package application

import builders.{LevelBuilder, RoomBuilder}
import command.Commands
import item._
import organism.{ItemCount, Player}
import world.Link

object UnitTest extends App{
  LevelBuilder.createWorld()
  val player =Commands.player
  val potion = new Potion("pot","s",10)
  val wep = new Weapon("wep","s",10)
  val wep1 = new Weapon("1wep","s",50)
  val manapotion = new ManaPotion("mp_pot","minor mana potion",25);
  val armor = new Armor("helmet","an old mail helmet",7);
  val link0= new Link(true,RoomBuilder.thirdRoom(),RoomBuilder.secondRoom(),"old_door",
    "it appears to be locked")
  val key:Key = new Key("brass_key","it's rusty",link0,"it's open")

  player.getInventory.put(potion.name,new ItemCount(potion,2))
  player.getInventory.put(manapotion.name,new ItemCount(manapotion,1))
  player.getInventory.put(armor.name,new ItemCount(armor,1))
  player.getInventory.put(key.name,new ItemCount(key,1))
  player.getInventory.put(wep.name,new ItemCount(wep,1))
  player.getInventory.put(wep1.name,new ItemCount(wep1,1))

  println(player.getInventory)
//  println(player.attack)
//  player.equip(wep)
//  println(player.attack)

//  player.equip(armor)

//  Commands.unequip("armor")

  Commands.use("pot")
  println(player.getInventory)


}
