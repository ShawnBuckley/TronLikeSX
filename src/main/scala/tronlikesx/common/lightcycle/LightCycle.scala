package tronlikesx.common.lightcycle

import rlsx.Game
import rlsx.display.{Codepage437, DisplayObject}
import rlsx.map.{MapObject, MapTile}
import rlsx.time.{ActionTime, TimeObject, TimedMove}
import rlsx.math.Vec2

import scala.collection.mutable

class LightCycle(color: String) extends MapObject(new DisplayObject('B', Codepage437.square, color), ActionTime(tick = 1000)) with TimeObject {
  var energy = 0

  var dropWalls = true

  private var alive = true
  private var oldVector: Vec2 = null

  private val walls = new mutable.ListBuffer[MapObject]

  Game.session.time.link(this)

  private var _vector = new Vec2(0)

  def vector = _vector

  def vector_=(newVec: Vec2): Unit = {
    if(alive) {
      oldVector = _vector
      _vector = newVec
      val chars = _vector match {
        case Vec2(-1, 0) => ('<', Codepage437.triangle_left)
        case Vec2( 0, 1) => ('v', Codepage437.triangle_down)
        case Vec2( 0,-1) => ('^', Codepage437.triangle_up)
        case Vec2( 1, 0) => ('>', Codepage437.triangle_right)
        case default => ('B', Codepage437.square)
      }
      display = new DisplayObject(chars._1, chars._2, display.color)
    }
  }

  override def move(newVec: Vec2): Boolean = {
    if(((newVec.x != 0 && newVec.y == 0) || (newVec.x == 0 && newVec.y != 0)) && newVec != vector && -newVec != vector) {
      vector = newVec
      true
    } else false
  }

  private def crash(): Unit = {
    alive = false
    flags.solid = false
    display = new DisplayObject('X', color)
  }

  override def tick(time: Int): Unit = {
    if(alive) {
      if (vector != Vec2(0, 0)) {
        energy += time
        while (energy - speed.tick >= 0) {
          energy -= speed.tick
          val newLocation = location + vector
          Game.session.map.get(newLocation) match {
            case None =>
              crash()
            case Some(tile: MapTile) =>
              if (flags.solid && (tile.terrain.flags.solid || !tile.mapObjects.forall(!_.flags.solid))) {
                crash()
              } else {
                if (dropWalls) {
                  val chars = if (oldVector != null && oldVector != Vec2(0, 0)) {
                    (if (vector.y != 0) -(vector + oldVector) else vector + oldVector) match {
                      case Vec2(1, 1) => ('\\', Codepage437.single_up_and_right)
                      case Vec2(-1, 1) => ('/', Codepage437.single_up_and_left)
                      case Vec2(1, -1) => ('/', Codepage437.single_down_and_right)
                      case Vec2(-1, -1) => ('\\', Codepage437.single_down_and_left)
                    }
                  } else {
                    if (vector.x != 0)
                      ('-', Codepage437.horizontal_line)
                    else
                      ('|', Codepage437.vertical_line)
                  }
                  val wall = new MapObject(new DisplayObject(chars._1, chars._2, color), ActionTime())
                  wall.location = location
                  walls += wall
                }
                location = newLocation
              }
          }
        }
        oldVector = null
      }
    } else {
      try {
        walls.remove(0).location = null
      } catch {
        case e: IndexOutOfBoundsException =>
          Game.session.time.unlink(this)
      }

    }
  }

  override def add(move: TimedMove): Unit = {}
}
