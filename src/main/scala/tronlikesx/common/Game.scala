package tronlikesx.common

import tronlikesx.common.map.Map
import tronlikesx.common.tron.lightgrid.LightGrid

class Game(val map: Map) {

}

object Game {
  val session = new Game(new LightGrid(64, 64))
}
