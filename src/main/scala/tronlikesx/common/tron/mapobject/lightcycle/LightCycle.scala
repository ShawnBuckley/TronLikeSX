package tronlikesx.common.tron.mapobject.lightcycle

import tronlikesx.common.display.{Codepage437, DisplayObject}
import tronlikesx.common.map.MapObject
import tronlikesx.common.time.{ActionTime, TimeObject, TimedMove}
import tronlikesx.common.Game
import tronlikesx.common.math.Vec2

import scala.collection.mutable

class LightCycle(color: String, startLocation: Vec2) extends MapObject(new DisplayObject('B', Codepage437.square, color), startLocation, ActionTime(tick = 1000)) with TimeObject {
  var vector = new Vec2(0)
  var energy = 0

  var dropWalls = true

  var oldVector: Vec2 = null

  val walls = new mutable.ArrayBuffer[MapObject]

  Game.session.time.link(this)

  override def move(newVec2: Vec2): Boolean = {
    if(((newVec2.x != 0 && newVec2.y == 0) || (newVec2.x == 0 && newVec2.y != 0)) && -newVec2 != vector) {
      oldVector = vector
      vector = newVec2
      val chars = vector match {
        case Vec2(-1, 0) => ('<', Codepage437.triangle_left)
        case Vec2( 0, 1) => ('v', Codepage437.triangle_down)
        case Vec2( 0,-1) => ('^', Codepage437.triangle_up)
        case Vec2( 1, 0) => ('>', Codepage437.triangle_right)
        case default => ('B', Codepage437.square)
      }
      display = new DisplayObject(chars._1, chars._2, display.color)
      return true
    }
    false
  }

  override def tick(time: Int): Unit = {
    if(vector != Vec2(0,0)) {
      energy += time
      while(energy - speed.tick >= 0) {
        energy -= speed.tick
        val newLocation = location + vector
        val tile = Game.session.map.get(newLocation)
        if(!tile.terrain.flags.solid && tile.mapObjects.isEmpty) {
          if(dropWalls) {
            val chars = if(oldVector != null && oldVector != Vec2(0,0)) {
              (if(vector.y != 0) -(vector + oldVector) else vector + oldVector) match {
                case Vec2( 1, 1) => ('\\', Codepage437.single_up_and_right)
                case Vec2(-1, 1) => ('/', Codepage437.single_up_and_left)
                case Vec2( 1,-1) => ('/', Codepage437.single_down_and_right)
                case Vec2(-1,-1) => ('\\', Codepage437.single_down_and_left)
              }
            } else {
              if(vector.x != 0)
                ('-', Codepage437.horizontal_line)
              else
                ('|', Codepage437.vertical_line)
            }
            walls += new MapObject(new DisplayObject(chars._1, chars._2, color), location, ActionTime())
          }
          location = newLocation
        } else {
          display = new DisplayObject('X', 'X', color)
          Game.session.time.unlink(this)
        }
      }
      oldVector = null
    }
  }

  override def add(move: TimedMove): Unit = {}
}
