package tronlikesx.common.map

import tronlikesx.common.Game
import tronlikesx.common.display.DisplayObject
import tronlikesx.common.math.Vec2
import tronlikesx.common.time.ActionTime

class MapObject(var display: DisplayObject, private var _location: Vec2, var speed: ActionTime) {
  location = _location

  def location = _location

  def location_=(location: Vec2) = {
    if(location.x >= 0 && location.x < Game.session.map.width &&
      location.y >= 0 && location.y < Game.session.map.width) {
      Game.session.map.get(_location).unlink(this)
      _location = location
      Game.session.map.get(_location).link(this)
    }
  }

  def move(newVec2: Vec2): Boolean = {
    val newPosition = location + newVec2
    if(newPosition.x >= 0 && newPosition.x < Game.session.map.width &&
      newPosition.y >= 0 && newPosition.y < Game.session.map.width) {
      val newTile = Game.session.map.get(newPosition)
      if(!newTile.terrain.flags.solid) {
        Game.session.map.get(location).unlink(this)
        _location = newPosition
        newTile.link(this)
        return true
      }
    }
    false
  }
}
