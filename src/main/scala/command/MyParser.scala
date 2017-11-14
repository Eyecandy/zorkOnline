package command
class MyParser {
  def startParsing() =  {
    val scanner = new java.util.Scanner(System.in)
    while (true) {
      val x = scanner.nextLine()
      println(x)
    }
  }
}
