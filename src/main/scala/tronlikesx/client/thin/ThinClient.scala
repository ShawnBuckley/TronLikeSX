package tronlikesx.client.thin

import org.scalajs.dom._
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport("ThinClient")
class ThinClient extends JSApp {
  def main(): Unit = {
    val sprite = document.getElementById("sprite").asInstanceOf[HTMLImageElement]
    val canvas = document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]

    val renderer = new Renderer(sprite, canvas)

    val socket = new WebSocket("ws://localhost:8080")

    renderer.clear()
  }
}
