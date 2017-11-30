package item

import world._
abstract class Item(s:String, des: String) extends FatherOfObjects {
  override val name: String = s
  override var story: String = des
  override val pickable: Boolean = true

  override def isEquip = false
}


