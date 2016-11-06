package tronlikesx.client

import org.scalajs.dom
import org.scalajs.dom.window
import tronlikesx.common.Location
import tronlikesx.common.entity.Player

class Input(player: Player, onInput: () => Unit) {
  window.onkeyup = { (e: dom.KeyboardEvent) =>
    e.key match {
      case "h" => player.mapObject.move(new Location(-1, +0))
      case "j" => player.mapObject.move(new Location(+0, +1))
      case "k" => player.mapObject.move(new Location(+0, -1))
      case "l" => player.mapObject.move(new Location(+1, +0))
      case "y" => player.mapObject.move(new Location(-1, -1))
      case "u" => player.mapObject.move(new Location(+1, -1))
      case "b" => player.mapObject.move(new Location(-1, +1))
      case "n" => player.mapObject.move(new Location(+1, +1))
    }
    onInput()
  }
}
