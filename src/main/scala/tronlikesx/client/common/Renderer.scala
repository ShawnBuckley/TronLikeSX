package tronlikesx.client.common

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.HTMLCanvasElement
import tronlikesx.client.common.sprite.{SpriteSheet, TransparentSpriteSheet}

import scala.collection.mutable

abstract class Renderer(sprite: TransparentSpriteSheet, canvas: HTMLCanvasElement) {

  val context = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  canvas.width = window.innerWidth.toInt
  canvas.height = window.innerHeight.toInt

  def clear(): Unit = {
    context.fillStyle = "black"
    context.fillRect(0, 0, canvas.width, canvas.height)
  }

  def render(x: Int, y: Int, render: Char, color: String): Unit = {
    context.drawImage(sprite.get(color),
      (render % sprite.rows)*sprite.x, (render / sprite.cols)*sprite.y,
      sprite.x, sprite.y,
      sprite.x*x, sprite.y*y,
      sprite.x, sprite.y)
  }
}
