package tronlikesx.common

import tronlikesx.common.map.Map
import tronlikesx.common.time.GameTime
import tronlikesx.common.tron.lightgrid.LightGrid

class Game(val map: Map, val time: GameTime) {

}

object Game {
  var session: Game = _
}
