package tronlikesx.client.common.image

import org.scalajs.dom.CanvasRenderingContext2D
import org.scalajs.dom.raw.HTMLCanvasElement
import rlsx.display.Colors

trait Transparent {
  def transparentColor: String

  def setTransparency(canvas: HTMLCanvasElement): HTMLCanvasElement = {
    val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    val imageData = context.getImageData(0, 0, canvas.width, canvas.height)
    val data = imageData.data
    val transparency = Colors.convertToRGB(transparentColor)
    for(i <- 0 until data.length by 4) {
      if(data(i) == transparency._1 && data(i+1) == transparency._2 && data(i+2) == transparency._3) {
        data(i+3) = 0
      }
    }
    context.putImageData(imageData, 0, 0)
    canvas
  }
}
