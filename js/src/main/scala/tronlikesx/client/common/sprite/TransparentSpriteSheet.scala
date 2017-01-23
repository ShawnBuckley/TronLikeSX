package tronlikesx.client.common.sprite

import scala.collection.mutable
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.client.common.image.{Colorable, Transparent}

case class TransparentSpriteSheet(x: Int,
                                  y: Int,
                                  rows: Int,
                                  cols: Int,
                                  transparentColor: String,
                                  image: HTMLImageElement)
  extends SpriteSheet with Transparent with Colorable {

  val cached = new mutable.HashMap[String, HTMLCanvasElement]

  def get(color: String): HTMLCanvasElement = {
    cached.get(color) match {
      case Some(image: HTMLCanvasElement) =>
        image
      case None =>
        val sheet = setTransparency(colorSheet(color, image))
        cached.put(color, sheet)
        sheet
    }
  }
}
