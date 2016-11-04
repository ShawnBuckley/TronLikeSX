package tronlikesx.common.map

import scala.collection.mutable

case class MapTile(terrain: Terrain) {
  val mapObjects = new mutable.MutableList[MapObject]

}
