package tronlikesx.common.map

import tronlikesx.common.Location
import tronlikesx.common.display.DisplayObject

class MapObject(display: DisplayObject) {
  var location: Location = _

  def set(location: Location) =
    this.location = location
}
