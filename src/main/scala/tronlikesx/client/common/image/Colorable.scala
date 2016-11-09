package tronlikesx.client.common.image

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}

trait Colorable {
  def colorSheet(color: String, image: HTMLImageElement): HTMLCanvasElement = {
    val sheet = document.createElement("canvas").asInstanceOf[HTMLCanvasElement]
    sheet.height = image.height
    sheet.width = image.width

    val newContext = sheet.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    newContext.fillStyle = color
    newContext.fillRect(0, 0, image.width, image.height)
    newContext.drawImage(image, 0, 0)

    sheet
  }
}
