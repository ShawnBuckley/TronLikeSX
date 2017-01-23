package rlsx.Actor

import rlsx.mapobject.MapObject
import rlsx.time.TimedMove

trait Actor {
  var energy = 0
  val moves = new scala.collection.mutable.Queue[TimedMove]

  def mapObject: MapObject

  def next: Option[TimedMove] =
    moves.headOption

  def pop(): Option[TimedMove] = {
    try {
      Option(moves.dequeue())
    } catch {
      case e: Exception => None
    }
  }

  def tick(time: Int): Unit = {
    energy += time
    next match {
      case None =>
      case Some(move: TimedMove) =>
        if(move.time <= energy) {
          pop()
          move.move()
          energy -= move.time
        }
    }
  }
}
