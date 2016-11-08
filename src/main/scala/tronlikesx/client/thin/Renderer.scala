package tronlikesx.client.thin

import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.client.common
import tronlikesx.client.common.SpriteSheet
import tronlikesx.client.thin.json.JsonDisplay
import rlsx.map.Map

import scala.scalajs.js

class Renderer(sprite: SpriteSheet, canvas: HTMLCanvasElement) extends common.Renderer(sprite, canvas) {
  def render(map: js.Array[js.Array[js.Array[JsonDisplay]]]): Unit = {
    for(x <- 0 until map.length) {
      val col = map(x)
      for(y <- 0 until col.length) {
        val displayObjects = col(y)
        for(i <- 0 until displayObjects.length) {
          val displayObject = displayObjects(i)
          render(x, y, displayObject.render, displayObject.color)
        }
      }
    }
  }
}
