package tronlikesx.common.map

import tronlikesx.common.display.DisplayObject

case class Terrain(flags: TerrainFlags, display: DisplayObject)

case class TerrainFlags(render: Boolean, solid: Boolean)