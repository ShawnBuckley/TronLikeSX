package tronlikesx.common.entity

import tronlikesx.common.Game
import tronlikesx.common.map.MapObject
import tronlikesx.common.time.TimeObject

class Player(var mapObject: MapObject) extends Entity with TimeObject {
  Game.session.time.link(this)
}
