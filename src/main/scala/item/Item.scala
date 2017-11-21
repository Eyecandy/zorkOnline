package item
import organism._
abstract class Item(s:String) {
  private val name = s
  private val description = String


}
trait equipment{
  def equip(playerPara: Int):Int =0
}

trait recovery{
  def use(playerPara: Int, currentPara: Int): Int = 0
}
