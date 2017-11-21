package item

class Weapon(s:String, para:Int) extends Item(s:String) with equipment {
  private val name = s
  private val atk = para

  override def equip(playerATK: Int): Int = playerATK+atk

}
