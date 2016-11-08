package tronlikesx.client.common

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.collection.mutable

abstract class Renderer(sprite: SpriteSheet, canvas: HTMLCanvasElement) {
  val coloredSprites = new mutable.HashMap[String, HTMLCanvasElement]

  val context = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  canvas.width = window.innerWidth.toInt
  canvas.height = window.innerHeight.toInt

  def getSheet(color: String): HTMLCanvasElement = {
    coloredSprites.get(color) match {
      case Some(sheet: HTMLCanvasElement) => sheet
      case None =>
        val sheet = createSpriteColor(color, sprite)
        coloredSprites.put(color, sheet)
        sheet
    }
  }

  def createSpriteColor(color: String, sprite: SpriteSheet): HTMLCanvasElement = {
    val newSheet = document.createElement("canvas").asInstanceOf[HTMLCanvasElement]
    newSheet.height = sprite.image.height
    newSheet.width = sprite.image.width

    val newContext = newSheet.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    newContext.fillStyle = color
    newContext.fillRect(0, 0, sprite.image.width, sprite.image.height)
    newContext.drawImage(sprite.image, 0, 0)

    val imageData = newContext.getImageData(0, 0, sprite.image.width, sprite.image.height)
    val data = imageData.data
    for(i <- 0 until data.length by 4) {
      if(data(i) == 0 && data(i+1) == 0 && data(i+2) == 0) {
        data(i+3) = 0
      }
    }
    newContext.putImageData(imageData, 0, 0)
    newSheet
  }

  def clear(): Unit = {
    context.fillStyle = "black"
    context.fillRect(0, 0, canvas.width, canvas.height)
  }

  def render(x: Int, y: Int, render: Char, color: String): Unit = {
    context.drawImage(getSheet(color),
      (render % sprite.rows)*sprite.x, (render / sprite.cols)*sprite.y,
      sprite.x, sprite.y,
      sprite.x*x, sprite.y*y,
      sprite.x, sprite.y)
  }
}
