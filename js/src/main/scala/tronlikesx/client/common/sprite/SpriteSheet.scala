package tronlikesx.client.common.sprite

import org.scalajs.dom.raw.HTMLImageElement

trait SpriteSheet {
  def x: Int
  def y: Int
  def rows: Int
  def cols: Int
  def image: HTMLImageElement
}