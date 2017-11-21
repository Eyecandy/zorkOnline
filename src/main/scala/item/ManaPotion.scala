package item

class ManaPotion(s:String, para:Int) extends Item(s:String) with recovery {
  private val name = s
  private val recoverAmount = para

  override def use(playerMp: Int, currentMp: Int): Int ={ // need to find way to decrease quantity in inventory
    if (currentMp < playerMp){
      if (currentMp+recoverAmount < playerMp) currentMp+recoverAmount
      else playerMp
    }
    else currentMp
  }

}
