package tronlikesx.common

case class Location(x: Int, y: Int) {
  def +(other: Location) =
    new Location(x + other.x, y + other.y)
}
