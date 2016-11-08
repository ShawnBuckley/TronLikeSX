package tronlikesx.client.standalone

import org.scalajs.dom
import org.scalajs.dom.window
import rlsx.Game
import rlsx.entity.Player
import rlsx.math.Vec2
import rlsx.time.TimedMove

import scala.collection.immutable.HashMap

class Input(player: Player, onInput: () => Unit) {
  val moveDirection = HashMap[String, Vec2](
    ("h", Vec2.west),
    ("j", Vec2.south),
    ("k", Vec2.north),
    ("l", Vec2.east),
    ("y", Vec2.northwest),
    ("u", Vec2.northeast),
    ("b", Vec2.southwest),
    ("n", Vec2.southeast)
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
