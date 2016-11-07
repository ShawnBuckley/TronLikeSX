package tronlikesx.common.map

import tronlikesx.common.Game
import tronlikesx.common.display.DisplayObject
import tronlikesx.common.math.Vec2
import tronlikesx.common.time.ActionTime

class MapObject(var display: DisplayObject, var speed: ActionTime) {
  private var _location: Vec2 = null

  def location =
    _location

  private def mapLink() = {
    Game.session.map.get(_location) match {
      case Some(tile: MapTile) => tile.link(this)
      case None =>
    }
  }

  private def mapUnlink() = {
    if(_location != null) {
      Game.session.map.get(_location) match {
        case Some(tile: MapTile) => tile.unlink(this)
        case None =>
      }
    }
  }

  def location_=(location: Vec2) = {
    mapUnlink()
    _location = location
    mapLink()
  }

  def move(newVec2: Vec2): Boolean = {
    val newPosition = location + newVec2
    Game.session.map.get(newPosition) match {
      case Some(tile: MapTile) =>
        if(!tile.terrain.flags.solid) {
          location = newPosition
          return true
        }
      case None =>
    }
    false
  }
}
