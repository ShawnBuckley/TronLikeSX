package tronlikesx.common.map

import tronlikesx.common.Location
import tronlikesx.common.display.{Colors, DisplayObject}

import scala.collection.mutable

case class DefaultMap(width: Int, height: Int) extends Map {
  val tiles = new mutable.ArrayBuffer[mutable.ArrayBuffer[MapTile]](width)

  for(x <- 0 until width) {
    val row = new mutable.ArrayBuffer[MapTile](height)
    for(y <- 0 until height) {
      row.insert(y, null)
    }
    tiles.insert(x, row)
  }

  val solidRender = TerrainFlags(render = true, solid = true)
  val wallDisplay = DisplayObject('#', 177, Colors.white)
  val wall = Terrain(solidRender, wallDisplay)

  val floorDisplay = DisplayObject('.', 250, Colors.white)
  val floor = Terrain(TerrainFlags(render = true, solid = false), floorDisplay)

  // walls
  tiles(0)(0) = MapTile(wall)
  tiles(width-1)(height-1) = MapTile(wall)

  for(i <- 1 until width) {
    tiles(i)(0) = MapTile(wall)
    tiles(i)(height-1) = MapTile(wall)
  }

  for(i <- 1 until height) {
    tiles(0)(i) = MapTile(wall)
    tiles(width-1)(i) = MapTile(wall)
  }

  // floors
  for(x <- 1 until width-1) {
    for(y <- 1 until height-1) {
      tiles(x)(y) = MapTile(floor)
    }
  }

  override def get(location: Location): MapTile =
    tiles(location.x)(location.y)

  override def set(location: Location, tile: MapTile): Unit =
    tiles(location.x)(location.y) = tile
}