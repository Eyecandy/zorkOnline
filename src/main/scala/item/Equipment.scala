package item

class Equipment(s:String, des:String, para:Int) extends Item(s:String, des: String){
  private val name = s
  private val description = des
  private val parameter = para
  override def canEquip = true
  def getParameter()=parameter

}

class Weapon(s:String, des:String, para:Int) extends Equipment(s:String, des:String, para:Int)

class Armor(s:String, des:String, para:Int) extends Equipment(s:String, des:String, para:Int)