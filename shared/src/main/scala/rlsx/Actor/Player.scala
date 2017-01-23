package rlsx.Actor

import rlsx.mapobject.MapObject
import rlsx.time.GameTime

class Player(var mapObject: MapObject)(implicit time: GameTime) extends Actor {
  time.link(this)
}