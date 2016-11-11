package rlsx.mapobject

import rlsx.display.DisplayObject
import rlsx.map.{Map, MapTile}
import rlsx.math.Vec2
import rlsx.time.ActionTime

case class Flags(var solid: Boolean = true)

class MapObject(var display: DisplayObject, var speed: ActionTime, val flags: Flags = Flags(), map: Map) {
  private var _location: Vec2 = null

  def location =
    _location

  private def mapLink() =
    map.get(_location).fold()(_.link(this))

  private def mapUnlink() =
    if(_location != null) map.get(_location).fold()(_.unlink(this))

  def location_=(location: Vec2) = {
    mapUnlink()
    _location = location
    mapLink()
  }

  def move(newVec2: Vec2): Boolean = {
    val newPosition = location + newVec2
    map.get(newPosition) match {
      case Some(tile: MapTile) =>
        if(!flags.solid) {
          location = newPosition
          return true
        } else {
          if(tile.isVacant) {
            location = newPosition
            return true
          }
        }
      case None =>
    }
    false
  }
}
