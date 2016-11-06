package tronlikesx.common.time

import scala.collection.mutable
import scala.util.control.Breaks._

trait TimeObject {
  var energy: Int = 0

  val moves = new mutable.Queue[TimedMove]

  def tick(time: Int): Unit = {
    energy += time

    breakable {
      while (moves.nonEmpty) {
        val next = moves.dequeue()
        if (next.time >= energy) {
          energy -= next.time
          next.callback()
        } else break
      }
    }
  }
}
