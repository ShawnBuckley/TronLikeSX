package rlsx.entity

import rlsx.Game
import rlsx.map.MapObject
import rlsx.time.{TimeObject, TimedMove}

import scala.collection.mutable
import scala.util.control.Breaks._

class Player(var mapObject: MapObject) extends Entity with TimeObject {
  Game.session.time.link(this)
}