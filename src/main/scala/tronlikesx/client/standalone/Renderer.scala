package tronlikesx.client.standalone

import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.common.Location
import tronlikesx.common.display.DisplayObject
import tronlikesx.common.map.{Map, MapObject}
import tronlikesx.client.common

class Renderer(sprite: HTMLImageElement, canvas: HTMLCanvasElement) extends common.Renderer(sprite, canvas) {
  def render(x: Int, y: Int, display: DisplayObject): Unit = {
    context.drawImage(getSheet(display.color),
      (display.char % 16)*12, (display.char / 16)*12,
      12, 12,
      12*x, 12*y,
      12, 12)
  }

  def render(map: Map): Unit = {
    for(x <- 0 until map.width) {
      for(y <- 0 until map.height) {
        val tile = map.get(new Location(x, y))
        render(x, y, tile.terrain.display)
        tile.mapObjects.lastOption match {
          case Some(mapObject: MapObject) =>
            render(x, y, mapObject.display)
          case None =>
        }
      }
    }
  }
}
