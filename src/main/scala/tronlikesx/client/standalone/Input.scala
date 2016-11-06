package tronlikesx.client.standalone

import org.scalajs.dom
import org.scalajs.dom.window
import tronlikesx.common.{Game, Location}
import tronlikesx.common.entity.Player
import tronlikesx.common.time.TimedMove

import scala.collection.immutable.HashMap

class Input(player: Player, onInput: () => Unit) {
  val moveDirection = HashMap[String, Location](
    ("h", new Location(-1, 0)),
    ("j", new Location( 0,+1)),
    ("k", new Location( 0,-1)),
    ("l", new Location(+1, 0)),
    ("y", new Location(-1,-1)),
    ("u", new Location(+1,-1)),
    ("b", new Location(-1,+1)),
    ("n", new Location(+1,+1))
  )

  window.onkeydown = { (e: dom.KeyboardEvent) =>
    e.key match {
      case "h" | "j" | "k" | "l" | "y" | "u" | "b" | "n" =>
        player.moves += new TimedMove(6000, () => {
          player.mapObject.move(moveDirection.get(e.key).get)
        })
        Game.session.time.tick(6000)
      case "." =>
        Game.session.time.tick(1000)
      case default =>
    }
    onInput()
  }
}
