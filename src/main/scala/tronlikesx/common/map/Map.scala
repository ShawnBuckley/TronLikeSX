package tronlikesx.common.map

import tronlikesx.common.math.Vec2

trait Map {
  def width: Int
  def height: Int
  def inBounds(location: Vec2): Boolean
  def get(location: Vec2): Option[MapTile]
  def set(location: Vec2, mapTile: MapTile): Unit
}
