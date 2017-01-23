package rlsx.map

import rlsx.math.Vec2

import scala.collection.mutable

trait Map {
  val tiles = new mutable.ArrayBuffer[mutable.ArrayBuffer[MapTile]]

  def width: Int
  def height: Int

  def inBounds(x: Int, y: Int): Boolean =
    x >= 0 && x < width && y >= 0 && y < height

  def inBounds(location: Vec2): Boolean =
    location != null && inBounds(location.x, location.y)

  def get(x: Int, y: Int): Option[MapTile] = {
    if(inBounds(x, y))
      Some(tiles(x)(y))
    else
      None
  }

  def get(location: Vec2): Option[MapTile] = {
    if(inBounds(location))
      Some(tiles(location.x)(location.y))
    else
      None
  }

  def set(x: Int, y: Int, tile: MapTile): Unit = {
    if(inBounds(x, y))
      tiles(x)(y) = tile
  }

  def set(location: Vec2, tile: MapTile): Unit = {
    if(inBounds(location))
      tiles(location.x)(location.y) = tile
  }
}
