package tronlikesx.client.standalone

import org.scalajs.dom
import org.scalajs.dom.window
import rlsx.Actor.Player
import rlsx.math.Vec2
import rlsx.time.{GameTime, TimedMove}

import scala.collection.immutable.HashMap

class Input(player: Player, time: GameTime, onInput: () => Unit) {
  val moveDirection = HashMap[String, Vec2](
    ("a", Vec2.west),
    ("s", Vec2.south),
    ("w", Vec2.north),
    ("d", Vec2.east),
    ("q", Vec2.northwest),
    ("e", Vec2.northeast),
    ("z", Vec2.southwest),
    ("c", Vec2.southeast),

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
    moveDirection.get(e.key) match {
      case Some(vec: Vec2) =>
        player.moves += new TimedMove(player.mapObject.speed.movement, () => {
          player.mapObject.move(vec)
        })
        time.tick(player.mapObject.speed.movement)
      case None =>
        e.key match {
          case "x" | "." =>
            time.tick(1000)
          case "p" =>
            time.toggleRealTime()
          case default =>
            println(s"Unrecognized command '${e.key}'")
        }
    }
    onInput()
  }
}
