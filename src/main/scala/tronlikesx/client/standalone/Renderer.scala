package tronlikesx.client.standalone

import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.common.display.DisplayObject
import tronlikesx.common.map.{Map, MapTile}
import tronlikesx.client.common
import tronlikesx.common.math.Vec2

class Renderer(sprite: HTMLImageElement, canvas: HTMLCanvasElement) extends common.Renderer(sprite, canvas) {
  def render(x: Int, y: Int, display: DisplayObject): Unit =
    render(x, y, display.render, display.color)

  def render(map: Map): Unit = {
    for(x <- 0 until map.width) {
      for(y <- 0 until map.height) {
        map.get(new Vec2(x, y)) match {
          case Some(tile: MapTile) =>
            render(x, y, tile.terrain.display)
            tile.mapObjects.foreach(mapObject => render(x, y, mapObject.display))
          case None =>
        }
      }
    }
  }
}
