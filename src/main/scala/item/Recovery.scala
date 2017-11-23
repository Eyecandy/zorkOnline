package item

class Recovery(s:String, des:String, para:Int) extends Item(s:String, des: String){
  private val name = s
  private val description = des
  private val parameter = para
  override def canEquip = false
  def getParameter()=parameter
}

class Potion(s:String, des:String, para:Int) extends Recovery(s:String, des:String, para:Int)

class ManaPotion(s:String, des:String, para:Int) extends Recovery(s:String, des:String, para:Int)
