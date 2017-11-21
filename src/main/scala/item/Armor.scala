package item

class Armor(s:String, para:Int) extends Item(s:String) with equipment {
  private val name = s
  private val DEF = para

  override def equip(playerDEF: Int): Int = playerDEF+DEF

}
