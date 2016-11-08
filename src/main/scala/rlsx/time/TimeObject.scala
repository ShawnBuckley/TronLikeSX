package rlsx.time

import scala.collection.mutable
import scala.util.control.Breaks._

trait TimeObject {
  var energy = 0
  val moves = new mutable.Queue[TimedMove]

  def tick(time: Int): Unit = {
    breakable {
      while (moves.nonEmpty) {
        val next = moves.dequeue()
        if (next.time >= energy) {
          if (next.move()) energy -= next.time
        } else break
      }
    }
  }

  def add(move: TimedMove): Unit =
    moves += move
}
