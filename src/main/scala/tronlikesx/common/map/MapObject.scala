package tronlikesx.common.map

import tronlikesx.common.{Game, Location}
import tronlikesx.common.display.DisplayObject

class MapObject(val display: DisplayObject, var _location: Location) {
  location = _location

  def location = _location

  def location_=(location: Location) = {
    if(location.x >= 0 && location.x < Game.map.width &&
      location.y >= 0 && location.y < Game.map.width) {
      Game.map.get(_location).unlink(this)
      _location = location
      Game.map.get(_location).link(this)
    }
  }

  def move(newLocation: Location) = {
    val newPosition = location + newLocation
    if(newPosition.x >= 0 && newPosition.x < Game.map.width &&
      newPosition.y >= 0 && newPosition.y < Game.map.width) {
      val newTile = Game.map.get(newPosition)
      if(!newTile.terrain.flags.solid) {
        Game.map.get(location).unlink(this)
        _location = newPosition
        newTile.link(this)
      }
    }
  }
}
