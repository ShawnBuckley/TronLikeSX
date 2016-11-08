package rlsx.entity

import rlsx.Game
import rlsx.map.MapObject
import rlsx.time.{TimeObject, TimedMove}

import scala.collection.mutable
import scala.util.control.Breaks._

class Player(var mapObject: MapObject) extends Entity with TimeObject {
  Game.session.time.link(this)

  var energy = 0

  val moves = new mutable.Queue[TimedMove]

  override def tick(time: Int): Unit = {
    breakable {
      while (moves.nonEmpty) {
        val next = moves.dequeue()
        if (next.time >= energy) {
          if(next.move()) energy -= next.time
        } else break
      }
    }
  }

  override def add(move: TimedMove): Unit =
    moves += move
}
