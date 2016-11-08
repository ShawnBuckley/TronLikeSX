package tronlikesx.client.standalone

import org.scalajs.dom.raw.HTMLCanvasElement
import rlsx.display.DisplayObject
import rlsx.map.{Map, MapTile}
import tronlikesx.client.common
import tronlikesx.client.common.SpriteSheet
import rlsx.math.Vec2

class Renderer(sprite: SpriteSheet, canvas: HTMLCanvasElement) extends common.Renderer(sprite, canvas) {
  def render(x: Int, y: Int, display: DisplayObject): Unit =
    render(x, y, display.render, display.color)

  def render(map: Map): Unit = {

    val offset = ((((canvas.width/sprite.x)-map.width)/2)+1,
      (((canvas.height/sprite.y)-map.height)/2)+1)

    for(x <- 0 until map.width) {
      for(y <- 0 until map.height) {
        map.get(x, y) match {
          case Some(tile: MapTile) =>
            render(x + offset._1, y + offset._2, tile.terrain.display)
            tile.mapObjects.foreach(mapObject => render(x + offset._1, y + offset._2, mapObject.display))
          case None =>
        }
      }
    }
  }
}
