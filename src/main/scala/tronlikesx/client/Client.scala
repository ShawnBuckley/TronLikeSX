package tronlikesx.client

import org.scalajs.dom.document
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.common.map.GameMap

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport("Client")
object Client extends JSApp {

  def main(): Unit = {
    val map = new GameMap(10,15)
    map.init()

    val sprite = document.getElementById("sprite").asInstanceOf[HTMLImageElement]
    val canvas = document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]

    val renderer = new Renderer(sprite, canvas)

    renderer.clear()
    renderer.render(map)
  }

  def main(args: Array[String]) = {
    val map = new GameMap(10,15)
    map.init()

    println(s"${map.width} ${map.height}")

    for(x <- 0 until map.width) {
      for(y <- 0 until map.height) {
        val char = map.tiles(x)(y).terrain.display.char
        print(char)
      }
      println()
    }
  }
}
