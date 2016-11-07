package tronlikesx.client.standalone

import org.scalajs.dom.document
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.common.display.Colors
import tronlikesx.common.entity.Player
import tronlikesx.common.time.GameTime
import tronlikesx.common.tron.lightgrid.LightGrid
import tronlikesx.common.tron.mapobject.lightcycle.LightCycle
import tronlikesx.common.Game
import tronlikesx.common.math.Vec2

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport("StandaloneClient")
class StandaloneClient extends JSApp {
  def main(): Unit = {
    val sprite = document.getElementById("sprite").asInstanceOf[HTMLImageElement]
    val canvas = document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]

    val renderer = new Renderer(sprite, canvas)
    def render(): Unit = {
      renderer.clear()
      renderer.render(Game.session.map)
    }

    Game.session = new Game(new LightGrid(64, 64), new GameTime(false, new Timer(renderer)))

//    val player = new Player(new MapObject(new DisplayObject('@', '@', Colors.blue), new Vec2(4, 4), ActionTime(6000)))
    val player = new Player(new LightCycle(Colors.blue, new Vec2(4, 4)))
    val ai = new LightCycle(Colors.red, new Vec2(60, 60))
    ai.vector = Vec2(-1,0)

    render()
    val input = new Input(player, render)
  }
}