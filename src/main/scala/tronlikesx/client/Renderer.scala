package tronlikesx.client

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.common.map.{DefaultMap, GameMap}

import scala.collection.mutable

class Renderer(sprite: HTMLImageElement, canvas: HTMLCanvasElement) {
  val coloredSprites = new mutable.HashMap[String, HTMLCanvasElement]

  val context = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  canvas.width = window.screen.availWidth.toInt
  canvas.height = window.screen.availHeight.toInt

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
    newSheet.height = 192
    newSheet.width = 192

    val newContext = newSheet.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    newContext.fillStyle = color
    newContext.fillRect(0, 0, 192, 192)
    newContext.drawImage(sheet, 0, 0)

    val imageData = newContext.getImageData(0, 0, 192, 192)
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

  def render(map: GameMap): Unit = {
    for(x <- 0 until map.width) {
      for(y <- 0 until map.height) {
        val display = map.get(x, y).terrain.display
        context.drawImage(getSheet(display.color),
          (display.char % 16)*12, (display.char / 16)*12,
          12, 12,
          12*x, 12*y,
          12, 12)
      }
    }
  }
}
