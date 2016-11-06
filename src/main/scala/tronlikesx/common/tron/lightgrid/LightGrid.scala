package tronlikesx.common.tron.lightgrid

import tronlikesx.common.Location
import tronlikesx.common.display.{Colors, DisplayObject}
import tronlikesx.common.map.{GameMap, MapTile, Terrain, TerrainFlags}

import scala.collection.mutable

case class LightGrid(width: Int, height: Int) extends GameMap {
  val tiles = new mutable.ArrayBuffer[mutable.ArrayBuffer[MapTile]](width)

  val wallFlags = TerrainFlags(render = true, solid = true)
  val floorFlags = TerrainFlags(render = true, solid = false)

  val wall = Terrain(wallFlags, DisplayObject('#', 219, Colors.dark_blue))
  val floor1 = Terrain(floorFlags, DisplayObject('+', 197, Colors.dark_blue))
  val floor2 = Terrain(floorFlags, DisplayObject('-', 196, Colors.dark_blue))
  val floor3 = Terrain(floorFlags, DisplayObject('|', 179, Colors.dark_blue))
  val floor4 = Terrain(floorFlags, DisplayObject(' ', 255, Colors.dark_blue))

  for(x <- 0 until width) {
    val row = new mutable.ArrayBuffer[MapTile](height)
    for(y <- 0 until height) {
      if(x == 0 || x >= width-1 || y == 0 || y >= height-1) {
        row.insert(y, MapTile(wall))
      } else {
        if(y % 2 == 0)
          row.insert(y, if(x % 2 == 0) MapTile(floor1) else MapTile(floor2))
        else
          row.insert(y, if(x % 2 == 0) MapTile(floor3) else MapTile(floor4))
      }
    }
    tiles.insert(x, row)
  }

  override def get(location: Location): MapTile =
    tiles(location.x)(location.y)

  override def set(location: Location, tile: MapTile): Unit =
    tiles(location.x)(location.y) = tile
}