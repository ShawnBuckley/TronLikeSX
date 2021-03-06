package rlsx.display

object Colors {
  val black = "#000000"
  val white = "#ffffff"
  val red = "#ff0000"
  val green = "#00ff00"
  val blue = "#0000ff"
  val dark_blue = "#000040"

  def convertToRGB(hex: String): (Int, Int, Int) = {
    val string = hex.replace("#", "")
    (Integer.parseInt(string.substring(0, 2), 16),
      Integer.parseInt(string.substring(2, 4), 16),
      Integer.parseInt(string.substring(4, 6), 16))
  }
}
