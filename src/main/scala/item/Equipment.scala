package item

class Equipment(s:String, des:String, para:Int) extends Item(s:String, des: String){
  override val name = s
  val description = des
  val parameter = para
  val canEquip = true



  override def isEquip: Boolean = true
}

case class Weapon(s:String, des:String, para:Int) extends Equipment(s:String, des:String, para:Int)

case class Armor(s:String, des:String, para:Int) extends Equipment(s:String, des:String, para:Int)