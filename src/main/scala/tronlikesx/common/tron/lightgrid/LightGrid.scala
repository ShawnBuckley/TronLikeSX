package tronlikesx.common.tron.lightgrid

import tronlikesx.common.Location
import tronlikesx.common.display.{Codepage437, Colors, DisplayObject}
import tronlikesx.common.map.{Map, MapTile, Terrain, TerrainFlags}

import scala.collection.mutable

class LightGrid(val width: Int, val height: Int) extends Map {
  val tiles = new mutable.ArrayBuffer[mutable.ArrayBuffer[MapTile]](width)

  val wallFlags = TerrainFlags(render = true, solid = true)
  val floorFlags = TerrainFlags(render = true, solid = false)

  val wall = Terrain(wallFlags, DisplayObject('#', Codepage437.full_block, Colors.dark_blue))
  val floor1 = Terrain(floorFlags, DisplayObject('+', Codepage437.cross, Colors.dark_blue))
  val floor2 = Terrain(floorFlags, DisplayObject('-', Codepage437.horizontal_line, Colors.dark_blue))
  val floor3 = Terrain(floorFlags, DisplayObject('|', Codepage437.vertical_line, Colors.dark_blue))
  val floor4 = Terrain(floorFlags, DisplayObject(' ', Codepage437.space, Colors.dark_blue))

  for(x <- 0 until width) {
    val row = new mutable.ArrayBuffer[MapTile](height)
    for(y <- 0 until height) {
      if(x == 0 || x >= width-1 || y == 0 || y >= height-1) {
        row.insert(y, new MapTile(wall))
      } else {
        if(y % 2 == 0)
          row.insert(y, if(x % 2 == 0) new MapTile(floor1) else new MapTile(floor2))
        else
          row.insert(y, if(x % 2 == 0) new MapTile(floor3) else new MapTile(floor4))
      }
    }
    tiles.insert(x, row)
  }

  override def get(location: Location): MapTile =
    tiles(location.x)(location.y)

  override def set(location: Location, tile: MapTile): Unit =
    tiles(location.x)(location.y) = tile
}
