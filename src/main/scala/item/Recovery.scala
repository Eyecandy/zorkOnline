package item

class Recovery(s:String, des:String, para:Int) extends Item(s:String, des: String) {
  override val name = s
  private val description = des
  private val parameter = para

  def getParameter() = parameter
}



case class Potion(s:String, des:String, para:Int) extends Recovery(s:String, des:String, para:Int)

case class ManaPotion(s:String, des:String, para:Int) extends Recovery(s:String, des:String, para:Int)

