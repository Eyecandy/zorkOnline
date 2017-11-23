package item
<<<<<<< HEAD
import world._
abstract class Item(s:String, des: String) extends FatherOfObjects {
  override val name: String = s
  override var story: String = des
  override val pickable: Boolean = true

  override def isEquip = false
=======
import organism._
abstract class Item(s:String) {
  private val name = s
 // private val description = String
>>>>>>> fronEnd


}
//trait equipment{
//  def equip(playerPara: Int):Int =0
//}
//
//trait recovery{
//  def use(playerPara: Int, currentPara: Int): Int = 0
//}
