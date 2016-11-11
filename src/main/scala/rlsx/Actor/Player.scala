package rlsx.Actor

import rlsx.mapobject.MapObject
import rlsx.time.{GameTime, TimeObject}

class Player(var mapObject: MapObject)(implicit time: GameTime) extends Actor with TimeObject {
  time.link(this)
}