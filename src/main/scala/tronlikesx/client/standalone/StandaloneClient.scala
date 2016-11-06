package tronlikesx.client.standalone

import org.scalajs.dom.document
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.common.display.Colors
import tronlikesx.common.entity.Player
import tronlikesx.common.tron.mapobject.lightcycle.LightCycle
import tronlikesx.common.{Game, Location}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport("StandaloneClient")
class StandaloneClient extends JSApp {
  def main(): Unit = {
    val sprite = document.getElementById("sprite").asInstanceOf[HTMLImageElement]
    val canvas = document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]

//    val player = new Player(new MapObject(new DisplayObject('@', '@', Colors.blue), new Location(4, 4), ActionTime(6000)))
    val player = new Player(new LightCycle(Colors.blue, new Location(4, 4)))

    val renderer = new Renderer(sprite, canvas)

    def onInput(): Unit = {
      renderer.clear()
      renderer.render(Game.session.map)
    }

    onInput()
    val input = new Input(player, onInput)
  }
}
