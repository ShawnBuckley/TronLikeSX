package tronlikesx.common.tron.mapobject.lightcycle

import tronlikesx.common.display.{Codepage437, DisplayObject}
import tronlikesx.common.map.MapObject
import tronlikesx.common.time.{ActionTime, TimeObject, TimedMove}
import tronlikesx.common.Game
import tronlikesx.common.math.Vec2

class LightCycle(color: String, startLocation: Vec2) extends MapObject(new DisplayObject('B', Codepage437.square, color), startLocation, ActionTime(tick = 1000)) with TimeObject {
  var vector = new Vec2(0,0)
  var energy = 0

  Game.session.time.link(this)

  override def move(newVec2: Vec2): Boolean = {
    // Disallow ordinal movement
    if(((newVec2.x != 0 && newVec2.y == 0) || (newVec2.x == 0 && newVec2.y != 0)) && -newVec2 != vector) {
      vector = newVec2
      vector match {
        case Vec2(-1, 0) => display = new DisplayObject(display.print, Codepage437.triangle_left, display.color)
        case Vec2( 0, 1) => display = new DisplayObject(display.print, Codepage437.triangle_down, display.color)
        case Vec2( 0,-1) => display = new DisplayObject(display.print, Codepage437.triangle_up, display.color)
        case Vec2( 1, 0) => display = new DisplayObject(display.print, Codepage437.triangle_right, display.color)
      }
      return true
    }
    false
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
