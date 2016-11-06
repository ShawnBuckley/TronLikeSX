package tronlikesx.common.map

import scala.collection.mutable

class MapTile(val terrain: Terrain) {
  val mapObjects = new mutable.ListBuffer[MapObject]

  def link(mapObject: MapObject): Unit =
    mapObjects += mapObject

  def unlink(mapObject: MapObject): Unit =
    mapObjects -= mapObject
}
