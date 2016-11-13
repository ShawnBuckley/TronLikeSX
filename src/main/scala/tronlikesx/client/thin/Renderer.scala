package tronlikesx.client.thin

import org.scalajs.dom.raw.HTMLCanvasElement
import tronlikesx.client.common
import tronlikesx.client.thin.json.JsonDisplay
import tronlikesx.client.common.sprite.TransparentSpriteSheet

import scala.scalajs.js

class Renderer(sprite: TransparentSpriteSheet, canvas: HTMLCanvasElement) extends common.Renderer(sprite, canvas) {

  override def minWidth: Int = ???

  override def minHeight: Int = ???

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

  override def render(): Unit = ???
}
