package tronlikesx.client.standalone

import org.scalajs.dom.document
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import rlsx.Game
import tronlikesx.client.common.SpriteSheet
import rlsx.display.{Colors, DisplayObject}
import rlsx.entity.Player
import rlsx.mapobject.MapObject
import rlsx.time.{ActionTime, GameTime}
import rlsx.math.Vec2
import tronlikesx.common.lightcycle
import tronlikesx.common.lightcycle.LightCycle
import tronlikesx.common.lightgrid.LightGrid

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport("StandaloneClient")
class StandaloneClient extends JSApp {
  def main(): Unit = {
    val renderer = new Renderer(
      new SpriteSheet(12, 12, 16, 16, document.getElementById("sprite").asInstanceOf[HTMLImageElement]),
      document.getElementById("canvas").asInstanceOf[HTMLCanvasElement])

    def render(): Unit = {
      renderer.clear()
      renderer.render(Game.session.map)
    }

    Game.session = new Game(new LightGrid(64, 64), new GameTime(false, new Timer(renderer)))

//    val player = new Player(new MapObject(new DisplayObject('@', Colors.blue), ActionTime(6000)))
    val player = new Player(new LightCycle(Colors.blue))
    player.mapObject.location = new Vec2(4, 4)

    val ai = new lightcycle.ai.Basic(new LightCycle(Colors.red))
    ai.lightcycle.location = new Vec2(4, 60)
    ai.lightcycle.vector = Vec2.west

    render()
    val input = new Input(player, render)
  }
}