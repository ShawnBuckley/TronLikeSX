package tronlikesx.client.common

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}

object Renderer {
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
}
