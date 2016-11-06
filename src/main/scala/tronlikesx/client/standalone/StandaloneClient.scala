package tronlikesx.client.standalone

import org.scalajs.dom.document
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.common.display.{Colors, DisplayObject}
import tronlikesx.common.entity.Player
import tronlikesx.common.map.MapObject
import tronlikesx.common.tron.lightgrid.LightGrid
import tronlikesx.common.{Game, Location}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport("StandaloneClient")
class StandaloneClient extends JSApp {

  def main(): Unit = {
    val sprite = document.getElementById("sprite").asInstanceOf[HTMLImageElement]
    val canvas = document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]

    val player = new Player(new MapObject(new DisplayObject('@', '@', Colors.blue), new Location(4, 4)))

    val renderer = new Renderer(sprite, canvas)

    def onInput(): Unit = {
      renderer.clear()
      renderer.render(Game.session.map)
    }

    onInput()
    val input = new Input(player, onInput)
  }

  def main(args: Array[String]) = {
    val map = new LightGrid(64,64)
    for(x <- 0 until map.width) {
      for(y <- 0 until map.height) {
        val char = map.tiles(x)(y).terrain.display.print
        print(char)
      }
      println()
    }
  }
}
