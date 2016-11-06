package tronlikesx.client.standalone

import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.common.Location
import tronlikesx.common.display.DisplayObject
import tronlikesx.common.map.Map
import tronlikesx.client.common

class Renderer(sprite: HTMLImageElement, canvas: HTMLCanvasElement) extends common.Renderer(sprite, canvas) {
  def render(x: Int, y: Int, display: DisplayObject): Unit =
    render(x, y, display.char, display.color)

  def render(map: Map): Unit = {
    for(x <- 0 until map.width) {
      for(y <- 0 until map.height) {
        val tile = map.get(new Location(x, y))
        render(x, y, tile.terrain.display)
        tile.mapObjects.foreach(mapObject => render(x, y, mapObject.display))
      }
    }
  }
}
