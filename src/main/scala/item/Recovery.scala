package item

class Recovery(s:String, des:String, para:Int) extends Item(s:String, des: String) {
  override val name = s
  private val description = des
  val parameter = para
}



case class Potion(s:String, des:String, para:Int) extends Recovery(s:String, des:String, para:Int)

case class ManaPotion(s:String, des:String, para:Int) extends Recovery(s:String, des:String, para:Int)

