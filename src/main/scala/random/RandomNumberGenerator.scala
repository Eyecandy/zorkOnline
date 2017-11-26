package random
import scala.util._

object RandomNumberGenerator{
  def RNG(start: Int, end: Int):Int= { // start & end inclusive
    val r = new Random
    start + r.nextInt( (end - start) + 1 )
  }

  def RNG(end: Int):Int= { // end inclusive
    val r = new Random
    r.nextInt(end+1)
  }

  def RNG(start: Double, end: Double):Double= {
    val r = new Random
    start + r.nextDouble() * (end - start)
  }


}
