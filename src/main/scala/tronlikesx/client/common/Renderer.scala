package tronlikesx.client.common

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}

import scala.collection.mutable

abstract class Renderer(sprite: HTMLImageElement, canvas: HTMLCanvasElement) {
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

  def createSpriteColor(color: String, sheet: HTMLImageElement): HTMLCanvasElement = {
    val newSheet = document.createElement("canvas").asInstanceOf[HTMLCanvasElement]
    newSheet.height = sheet.height
    newSheet.width = sheet.width

    val newContext = newSheet.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    newContext.fillStyle = color
    newContext.fillRect(0, 0, sheet.width, sheet.height)
    newContext.drawImage(sheet, 0, 0)

    val imageData = newContext.getImageData(0, 0, sheet.width, sheet.height)
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
    context.clearRect(0, 0, canvas.width, canvas.height)
    context.fillStyle = "black"
    context.fillRect(0, 0, canvas.width, canvas.height)
  }

  def render(x: Int, y: Int, render: Char, color: String): Unit = {
    context.drawImage(getSheet(color),
      (render % 16)*12, (render / 16)*12,
      12, 12,
      12*x, 12*y,
      12, 12)
  }
}