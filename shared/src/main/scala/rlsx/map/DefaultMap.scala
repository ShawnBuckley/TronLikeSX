package rlsx.map

import rlsx.display.{Codepage437, Colors, DisplayObject}
import rlsx.math.Vec2

import scala.collection.mutable

class DefaultMap(val width: Int, val height: Int) extends Map {
  tiles.sizeHint(width)

  val wall = Terrain(TerrainFlags(render = true, solid = true), DisplayObject('#', Codepage437.medium_shade, Colors.white))
  val floor = Terrain(TerrainFlags(render = true, solid = false), DisplayObject('.', Codepage437.interpunct, Colors.white))

  for(x <- 0 until width) {
    val row = new mutable.ArrayBuffer[MapTile](height)
    for(y <- 0 until height) {
      if(x == 0 || y == 0 || x == width-1 || y == height-1)
        row.insert(y, new MapTile(wall))
      else
        row.insert(y, new MapTile(floor))
    }
    tiles.insert(x, row)
  }
}