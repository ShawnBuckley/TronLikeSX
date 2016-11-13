package tronlikesx.common.lightcycle.ai

import scala.util.control.Breaks._
import rlsx.Actor.Actor
import rlsx.map.Map
import rlsx.map.MapTile
import rlsx.mapobject.MapObject
import rlsx.math.Vec2
import rlsx.time.{GameTime, TimedMove}
import tronlikesx.common.lightcycle.LightCycle

class Basic(var lightcycle: LightCycle)(implicit map: Map, time: GameTime) extends Actor {
  time.link(this)

  override def mapObject: MapObject = lightcycle

  def turn(): Unit = {
    var i = 1
    val checkVector = if(lightcycle.vector.y != 0) Vec2(1,0) else Vec2(0,1)
    breakable {
      while(true) {
        def checkLocation(checkVector: Vec2): Boolean = {
          map.get(lightcycle.location + checkVector*i) match {
            case None =>
            case Some(tile: MapTile) =>
              if(!tile.isVacant) {
                println("turn!")
                lightcycle.vector = -checkVector
                return true
              }
          }
          false
        }
        if(checkLocation(checkVector) || checkLocation(-checkVector))
          break
        i+=1
      }
    }
  }

  override def next: Option[TimedMove] = {
    if(moves.isEmpty) {
      val newLocation = lightcycle.location + lightcycle.vector
      map.get(newLocation) match {
        case None =>
          moves += new TimedMove(0, () => {
            turn()
            true
          })
        case Some(tile: MapTile) =>
          if (lightcycle.flags.solid && !tile.isVacant)
            moves += new TimedMove(0, () => {
              turn()
              true
            })
          else
            moves += new TimedMove(lightcycle.speed.tick, () => true)
      }
    }
    moves.headOption
  }
}
