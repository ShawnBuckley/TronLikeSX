package tronlikesx.common.tron.mapobject.lightcycle

import tronlikesx.common.display.{Codepage437, DisplayObject}
import tronlikesx.common.map.MapObject
import tronlikesx.common.time.{ActionTime, TimeObject, TimedMove}
import tronlikesx.common.{Game, Location}

class LightCycle(color: String, startLocation: Location) extends MapObject(new DisplayObject('B', Codepage437.square, color), startLocation, ActionTime(tick = 1000)) with TimeObject {
  var vector = new Location(+1,0)
  var energy = 0

  Game.session.time.link(this)

  override def move(newLocation: Location): Boolean = {
    // Disallow ordinal movement
    if((newLocation.x != 0 && newLocation.y == 0) || (newLocation.x == 0 && newLocation.y != 0)) {
      vector = newLocation
      true
    } else false
  }

  override def tick(time: Int): Unit = {
    energy += time
    while(energy - speed.tick >= 0) {
      energy -= speed.tick
      val newLocation = location + vector
      if(!Game.session.map.get(newLocation).terrain.flags.solid) {
        location = newLocation
      } else {
        display = new DisplayObject('X', 'X', color)
        Game.session.time.unlink(this)
      }
    }
  }

  override def add(move: TimedMove): Unit = {}
}
