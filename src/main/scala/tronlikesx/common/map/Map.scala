package tronlikesx.common.map

import tronlikesx.common.math.Vec2

trait Map {
  def width: Int
  def height: Int
  def get(location: Vec2): MapTile
  def set(location: Vec2, mapTile: MapTile): Unit
}
