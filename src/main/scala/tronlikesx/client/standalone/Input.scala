package tronlikesx.client.standalone

import org.scalajs.dom
import org.scalajs.dom.window
import tronlikesx.common.Game
import tronlikesx.common.entity.Player
import tronlikesx.common.math.Vec2
import tronlikesx.common.time.TimedMove

import scala.collection.immutable.HashMap

class Input(player: Player, onInput: () => Unit) {
  val moveDirection = HashMap[String, Vec2](
    ("h", new Vec2(-1, 0)),
    ("j", new Vec2( 0,+1)),
    ("k", new Vec2( 0,-1)),
    ("l", new Vec2(+1, 0)),
    ("y", new Vec2(-1,-1)),
    ("u", new Vec2(+1,-1)),
    ("b", new Vec2(-1,+1)),
    ("n", new Vec2(+1,+1))
  )

  window.onkeydown = { (e: dom.KeyboardEvent) =>
    e.key match {
      case "h" | "j" | "k" | "l" | "y" | "u" | "b" | "n" =>
        player.moves += new TimedMove(player.mapObject.speed.movement, () => {
          player.mapObject.move(moveDirection.get(e.key).get)
        })
        Game.session.time.tick(player.mapObject.speed.movement)
      case "." =>
        Game.session.time.tick(1000)
      case "p" =>
        Game.session.time.toggleRealTime()
      case default =>
        println(s"Unrecognized command '${e.key}'")
    }
    onInput()
  }
}
