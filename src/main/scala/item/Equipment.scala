package item

class Equipment(s:String, des:String, para:Int) extends Item(s:String, des: String){
  override val name = s
  val description = des
  val parameter = para
  val canEquip = true
  def getParameter()=parameter



  override def isEquip: Boolean = true
}

class Weapon(s:String, des:String, para:Int) extends Equipment(s:String, des:String, para:Int)

class Armor(s:String, des:String, para:Int) extends Equipment(s:String, des:String, para:Int)