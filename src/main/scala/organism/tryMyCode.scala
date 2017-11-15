package organism

import scala.collection.mutable
import Player._

object tryMyCode extends App{
  val me = Player
  me.getInventory().put("ans", 10)
  println(me.getInventory())
  me.getInventory().put("a", 100)
  println(me.getInventory())
}
