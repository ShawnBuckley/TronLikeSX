package tronlikesx.common.map

import tronlikesx.common.{Game, Location}
import tronlikesx.common.display.DisplayObject

case class MapObject(display: DisplayObject, var location: Location) {
  set(location)
  def set(location: Location) = {
    Game.map.get(this.location).unlink(this)
    this.location = location
    Game.map.get(location).link(this)
  }

  def move(newLocation: Location) = {
    val newPosition = location + newLocation
    if(newPosition.x >= 0 && newPosition.x < Game.map.width &&
      newPosition.y >= 0 && newPosition.y < Game.map.width) {
      val newTile = Game.map.get(newPosition)
      if(!newTile.terrain.flags.solid) {
        Game.map.get(location).unlink(this)
        this.location = newPosition
        newTile.link(this)
      }
    }
  }
}
