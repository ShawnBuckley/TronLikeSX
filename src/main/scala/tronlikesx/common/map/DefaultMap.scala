package tronlikesx.common.map

import tronlikesx.common.display.DisplayObject

import scala.collection.mutable

case class DefaultMap(width: Int, height: Int) extends GameMap {
  val tiles = new mutable.ArrayBuffer[mutable.ArrayBuffer[MapTile]](width)

  for(x <- 0 until width) {
    val row = new mutable.ArrayBuffer[MapTile](height)
    for(y <- 0 until height) {
      row.insert(y, null) // new MapTile(null)
    }
    tiles.insert(x, row)
  }

  val solidRender = TerrainFlags(render = true, solid = true)
  val wallDisplay = DisplayObject('#', "#ffffff")
  val wall = Terrain(solidRender, wallDisplay)

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

  val floorDisplay = DisplayObject('.', "#ffffff")
  val floor = Terrain(TerrainFlags(render = true, solid = false), floorDisplay)

  for(x <- 1 until width-1) {
    for(y <- 1 until height-1) {
      tiles(x)(y) = MapTile(floor)
    }
  }

  override def get(x: Int, y: Int): MapTile =
    tiles(x)(y)

  override def set(x: Int, y: Int, tile: MapTile): Unit =
    tiles(x)(y) = tile
}