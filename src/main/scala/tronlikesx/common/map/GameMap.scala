package tronlikesx.common.map

import tronlikesx.common.Location

trait GameMap {
  def width: Int
  def height: Int
  def get(location: Location): MapTile
  def set(location: Location, mapTile: MapTile): Unit
}
