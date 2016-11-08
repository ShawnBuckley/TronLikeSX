package rlsx.map

import rlsx.math.Vec2

import scala.collection.mutable

trait Map {
  val tiles = new mutable.ArrayBuffer[mutable.ArrayBuffer[MapTile]]

  def width: Int
  def height: Int

  def inBounds(location: Vec2): Boolean =
    location != null && location.x >= 0 && location.x < width && location.y >= 0 && location.y < height

  def get(location: Vec2): Option[MapTile] = {
    if(inBounds(location))
      Some(tiles(location.x)(location.y))
    else
      None
  }

  def set(location: Vec2, tile: MapTile): Unit = {
    if(inBounds(location))
      tiles(location.x)(location.y) = tile
  }
}
