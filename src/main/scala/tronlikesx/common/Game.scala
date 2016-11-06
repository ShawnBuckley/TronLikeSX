package tronlikesx.common

import tronlikesx.common.tron.lightgrid.LightGrid

import scala.scalajs.js.annotation.JSExport

@JSExport
object Game {
  val map = LightGrid(64, 64)
}
