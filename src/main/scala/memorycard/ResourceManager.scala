package memorycard

import java.io.{FileOutputStream, ObjectInputStream, ObjectOutputStream}
import java.nio.file.{Files, Paths}


object ResourceManager {

  def save(data:Serializable, fileName:String)= {

    val path = Paths.get("save1.txt")
    val file = Files.newOutputStream(path)
    val oos = new ObjectOutputStream(file)
    oos.writeObject(data)

  }

  def load(fileName:String) = {
      val path = Paths.get("save1.txt")
      val file = Files.newInputStream(path)
      val ois = new ObjectInputStream(file)
      ois.readObject()

  }


}
