package application

import builders.{LevelBuilder, RoomBuilder}
import command.Commands
import item.{Potion, Weapon}
import organism.{ItemCount, Player}
import organism.Monster

object InventoryTest extends App{
  LevelBuilder.createWorld()
  val player = Commands.player;
  assert(player.getInventory.size == 0)
  val potion = new Potion("pot","s",10)
  val wep = new Weapon("wep","s",10)
  player.getInventory.put(potion.name,new ItemCount(potion,2))
  player.hp = 88;
  assert(player.use(potion).equals("<br> You recovered for 10hp"))
  assert(player.hp == 98)
  assert(player.use(potion).equals("<br> You recovered for 2hp"))
  assert(player.hp == 100)
  assert(player.use(potion).equals("No such item in your inventory"))
  assert(player.hp == 100)
  assert(player.use(wep).equals("No such item in your inventory"))
  assert(player.getInventory.size==0)

  player.getInventory.put(wep.name,new ItemCount(wep,1));
  val bfDmg = player.attack
  player.use(wep)
  assert(player.attack == bfDmg + wep.para)

  //combat
  player.setDirection("s")
  val hp0 = player.hp
  println(hp0)
  val wfhp = player.getDirection().itemMap.get("wolfy").get.asInstanceOf[Monster].hp;
  println(player.attack("wolfy"))
  println(wfhp > wfhp1)
  val wfhp1 = player.getDirection().itemMap.get("wolfy").get.asInstanceOf[Monster].hp;
  val hp1 = player.hp
  assert(wfhp > wfhp1)

  assert(hp0 > hp1)
  player.getDirection().itemMap.get("wolfy").get.asInstanceOf[Monster].hp = 0;
  println("okaf")
  println(player.attack("wolfy"))
  assert(player.attack("wolfy").equals("wolfy is already dead"))
  println(assert(player.attack("something").equals("No such thing to attack here")))

  player.setDirection("e")
  println(assert(player.attack("wolfy").equals("No such thing to attack here")))






}
