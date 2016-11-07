package tronlikesx.common.tron.mapobject.lightcycle

import tronlikesx.common.display.{Codepage437, DisplayObject}
import tronlikesx.common.map.MapObject
import tronlikesx.common.time.{ActionTime, TimeObject, TimedMove}
import tronlikesx.common.Game
import tronlikesx.common.math.Vec2

class LightCycle(color: String, startLocation: Vec2) extends MapObject(new DisplayObject('B', Codepage437.square, color), startLocation, ActionTime(tick = 1000)) with TimeObject {
  var vector = new Vec2(+1,0)
  var energy = 0

  Game.session.time.link(this)

  override def move(newVec2: Vec2): Boolean = {
    // Disallow ordinal movement
    if((newVec2.x != 0 && newVec2.y == 0) || (newVec2.x == 0 && newVec2.y != 0)) {
      display = new DisplayObject(display.print, 'B', display.color)
      vector = newVec2
      true
    } else false
  }

  override def tick(time: Int): Unit = {
    energy += time
    while(energy - speed.tick >= 0) {
      energy -= speed.tick
      val newVec2 = location + vector
      if(!Game.session.map.get(newVec2).terrain.flags.solid) {
        location = newVec2
      } else {
        display = new DisplayObject('X', 'X', color)
        Game.session.time.unlink(this)
      }
    }
  }

  override def add(move: TimedMove): Unit = {}
}
