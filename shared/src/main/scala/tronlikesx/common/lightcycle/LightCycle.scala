package tronlikesx.common.lightcycle

import rlsx.display.{Codepage437, DisplayObject}
import rlsx.map.{Map, MapTile}
import rlsx.mapobject.MapObject
import rlsx.time.{ActionTime, GameTime, TimeObject}
import rlsx.math.Vec2

import scala.collection.mutable

class LightCycle(color: String)(implicit map: Map, time: GameTime) extends MapObject(new DisplayObject('B', Codepage437.square, color), ActionTime(tick = 1000)) with TimeObject {
  var dropWalls = true

  private var alive = true
  private var lastVector: Vec2 = Vec2.addIdent

  private val walls = new mutable.ListBuffer[MapObject]

  time.link(this)

  private var _vector = Vec2.addIdent

  def vector = _vector

  def vector_=(newVec: Vec2): Unit = {
    if(alive) {
      _vector = newVec
      val chars = _vector match {
        case Vec2.west  => ('<', Codepage437.triangle_left)
        case Vec2.south => ('v', Codepage437.triangle_down)
        case Vec2.north => ('^', Codepage437.triangle_up)
        case Vec2.east  => ('>', Codepage437.triangle_right)
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

  def dropWall(): Unit = {
    val chars = if (lastVector != vector && lastVector != Vec2(0, 0)) {
      (if (vector.y != 0) -(vector + lastVector) else vector + lastVector) match {
        case Vec2.southeast => ('\\', Codepage437.single_up_and_right)
        case Vec2.southwest => ('/',  Codepage437.single_up_and_left)
        case Vec2.northeast => ('/',  Codepage437.single_down_and_right)
        case Vec2.northwest => ('\\', Codepage437.single_down_and_left)
      }
    } else {
      if(vector.x != 0)
        ('-', Codepage437.horizontal_line)
      else
        ('|', Codepage437.vertical_line)
    }
    val wall = new MapObject(new DisplayObject(chars._1, chars._2, color), ActionTime())
    wall.location = location
    lastVector = _vector
    walls += wall
  }

  override def tick(time: Int): Unit = {
    if (vector != Vec2.addIdent) {
      energy += time
      while (energy - speed.tick >= 0) {
        energy -= speed.tick
        if(alive) {
          val newLocation = location + vector
          map.get(newLocation) match {
            case None =>
              crash()
            case Some(tile: MapTile) =>
              if (flags.solid && !tile.isVacant) {
                crash()
              } else {
                if (dropWalls) dropWall()
                location = newLocation
              }
          }
        } else {
          try {
            walls.remove(0).location = null
          } catch {
            case e: IndexOutOfBoundsException =>
              this.time.unlink(this)
          }
        }
      }
    }
  }
}
