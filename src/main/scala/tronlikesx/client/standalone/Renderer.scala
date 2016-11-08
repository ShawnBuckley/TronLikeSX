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

    val offset = ((((canvas.width/12)-map.width)/2)+1,
      (((canvas.height/12)-map.height)/2)+1)

    for(x <- 0 until map.width) {
      for(y <- 0 until map.height) {
        map.get(new Vec2(x, y)) match {
          case Some(tile: MapTile) =>
            render(x + offset._1, y + offset._2, tile.terrain.display)
            tile.mapObjects.foreach(mapObject => render(x + offset._1, y + offset._2, mapObject.display))
          case None =>
        }
      }
    }
  }
}
