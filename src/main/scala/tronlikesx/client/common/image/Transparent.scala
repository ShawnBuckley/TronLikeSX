package tronlikesx.client.common.image

import org.scalajs.dom.CanvasRenderingContext2D
import org.scalajs.dom.raw.HTMLCanvasElement

trait Transparent {
  def transparentColor: String

  def setTransparency(canvas: HTMLCanvasElement): HTMLCanvasElement = {
    val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    val imageData = context.getImageData(0, 0, canvas.width, canvas.height)
    val data = imageData.data
    for(i <- 0 until data.length by 4) {
      if(data(i) == 0 && data(i+1) == 0 && data(i+2) == 0) {
        data(i+3) = 0
      }
    }
    context.putImageData(imageData, 0, 0)
    canvas
  }
}
