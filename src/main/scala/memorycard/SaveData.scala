package memorycard


import organism.Player


@SerialVersionUID(114L)
class SaveData extends  Serializable {
  var player = new Player()
  var x:Double = 1.0
}
