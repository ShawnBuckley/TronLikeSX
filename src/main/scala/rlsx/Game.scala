package rlsx

import rlsx.map.Map
import rlsx.time.GameTime


class Game(val map: Map, val time: GameTime) {

}

object Game {
  var session: Game = _
}
