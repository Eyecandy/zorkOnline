package random
import scala.collection.mutable
import scala.util._

object RandomNumberGenerator{

  case class StatsRange(hp: (Int, Int), atk: (Int, Int), dff: (Int, Int), exp: (Int, Int))

  val monsterStatsMap = mutable.HashMap[Boolean, StatsRange]()
  monsterStatsMap.put(false, StatsRange((20, 50),(15, 30),(1, 5), (10, 20)))
  monsterStatsMap.put(true, StatsRange((60, 100),(25, 40),(1, 5), (20, 40)))

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
