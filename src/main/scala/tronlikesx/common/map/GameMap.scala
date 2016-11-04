package tronlikesx.common.map

import scala.collection.mutable

case class GameMap(width: Int, height: Int) {
  val tiles = new mutable.ArrayBuffer[mutable.ArrayBuffer[MapTile]](width)

  for(x <- 0 until width) {
    val row = new mutable.ArrayBuffer[MapTile](height)
    for(y <- 0 until height) {
      row.insert(y, null) // new MapTile(null)
    }
    tiles.insert(x, row)
  }
}

object GameMap {
  def main(args: Array[String]) = {
    val map = new GameMap(10, 10)
    val tile = map.tiles(1)(1)
  }
}
