package tronlikesx.common

import tronlikesx.common.map.Map
import tronlikesx.common.time.GameTime
import tronlikesx.common.tron.lightgrid.LightGrid

class Game(val map: Map, val time: GameTime) {

}

object Game {
  val session = new Game(new LightGrid(64, 64), new GameTime(false))
}
