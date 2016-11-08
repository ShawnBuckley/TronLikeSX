package tronlikesx.client.thin

import org.scalajs.dom._
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.client.common.SpriteSheet

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport("ThinClient")
class ThinClient extends JSApp {
  def main(): Unit = {
    val renderer = new Renderer(
      new SpriteSheet(12, 12, 16, 16, document.getElementById("sprite").asInstanceOf[HTMLImageElement]),
      document.getElementById("canvas").asInstanceOf[HTMLCanvasElement])

    val socket = new WebSocket("ws://localhost:8080")

    renderer.clear()
  }
}
