package tronlikesx.common.display

case class DisplayObject(print: Char, render: Char, color: String) {
  def this(print: Char, color: String) =
    this(print, print, color)
}
