package rlsx.entity

import rlsx.mapobject.MapObject
import rlsx.time.{GameTime, TimeObject}

class Player(var mapObject: MapObject)(implicit time: GameTime) extends Entity with TimeObject {
  time.link(this)
}