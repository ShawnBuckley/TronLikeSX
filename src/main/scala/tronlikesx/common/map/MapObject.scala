package tronlikesx.common.map

import tronlikesx.common.{Game, Location}
import tronlikesx.common.display.DisplayObject

class MapObject(val display: DisplayObject, var coord: Location) {
  set(coord)

  def location = coord

  def set(location: Location) = {
    if(location.x >= 0 && location.x < Game.map.width &&
      location.y >= 0 && location.y < Game.map.width) {
      Game.map.get(this.location).unlink(this)
      coord = location
      Game.map.get(location).link(this)
    }
  }

  def move(newLocation: Location) = {
    val newPosition = location + newLocation
    if(newPosition.x >= 0 && newPosition.x < Game.map.width &&
      newPosition.y >= 0 && newPosition.y < Game.map.width) {
      val newTile = Game.map.get(newPosition)
      if(!newTile.terrain.flags.solid) {
        Game.map.get(location).unlink(this)
        coord = newPosition
        newTile.link(this)
      }
    }
  }
}
