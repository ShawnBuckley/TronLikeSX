package tronlikesx.common.map

trait GameMap {
  def width: Int
  def height: Int
  def get(x: Int, y: Int): MapTile
  def set(x: Int, y: Int, tile: MapTile): Unit
}
