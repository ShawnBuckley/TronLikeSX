package tronlikesx.common.map

import tronlikesx.common.display.{Codepage437, Colors, DisplayObject}
import tronlikesx.common.math.Vec2

import scala.collection.mutable

class DefaultMap(val width: Int, val height: Int) extends Map {
  val tiles = new mutable.ArrayBuffer[mutable.ArrayBuffer[MapTile]](width)

  for(x <- 0 until width) {
    val row = new mutable.ArrayBuffer[MapTile](height)
    for(y <- 0 until height) {
      row.insert(y, null)
    }
    tiles.insert(x, row)
  }

  val solidRender = TerrainFlags(render = true, solid = true)
  val wallDisplay = DisplayObject('#', Codepage437.medium_shade, Colors.white)
  val wall = Terrain(solidRender, wallDisplay)

  val floorDisplay = DisplayObject('.', Codepage437.interpunct, Colors.white)
  val floor = Terrain(TerrainFlags(render = true, solid = false), floorDisplay)

  // walls
  tiles(0)(0) = new MapTile(wall)
  tiles(width-1)(height-1) = new MapTile(wall)

  for(i <- 1 until width) {
    tiles(i)(0) = new MapTile(wall)
    tiles(i)(height-1) = new MapTile(wall)
  }

  for(i <- 1 until height) {
    tiles(0)(i) = new MapTile(wall)
    tiles(width-1)(i) = new MapTile(wall)
  }

  // floors
  for(x <- 1 until width-1) {
    for(y <- 1 until height-1) {
      tiles(x)(y) = new MapTile(floor)
    }
  }

  override def inBounds(location: Vec2): Boolean =
    location.x >= 0 && location.x < width && location.y >= 0 && location.y < height

  override def get(location: Vec2): Option[MapTile] = {
    if(inBounds(location))
      Some(tiles(location.x)(location.y))
    else
      None
  }

  override def set(location: Vec2, tile: MapTile): Unit =
    tiles(location.x)(location.y) = tile
}