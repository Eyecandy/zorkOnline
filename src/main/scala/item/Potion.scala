package item
 // might have to make Player Class instead of object to modify inventory
class Potion(s:String, para:Int) extends Item(s:String) with recovery {
  private val name = s
  private val recoverAmount = para

  override def use(playerHp: Int, currentHp: Int): Int ={ // need to find way to decrease quantity in inventory
    if (currentHp < playerHp){
      if (currentHp+recoverAmount < playerHp) currentHp+recoverAmount
      else playerHp
    }
    else currentHp
  }

}
