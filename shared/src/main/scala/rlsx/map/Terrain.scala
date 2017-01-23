package rlsx.map

import rlsx.display.DisplayObject

case class Terrain(flags: TerrainFlags, display: DisplayObject)

case class TerrainFlags(render: Boolean, solid: Boolean)