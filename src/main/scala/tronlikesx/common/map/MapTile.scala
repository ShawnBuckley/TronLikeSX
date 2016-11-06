package tronlikesx.common.map

import scala.collection.mutable

case class MapTile(terrain: Terrain) {
  val mapObjects = new mutable.ListBuffer[MapObject]

  def link(mapObject: MapObject) =
    mapObjects += mapObject

  def unlink(mapObject: MapObject): Unit =
    mapObjects -= mapObject
}
