package tronlikesx.client

import org.scalajs.dom.document
import org.scalajs.dom.raw.{HTMLCanvasElement, HTMLImageElement}
import tronlikesx.common.display.DisplayObject
import tronlikesx.common.map.{GameMap, MapTile, Terrain, TerrainFlags}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport("Client")
object Client extends JSApp {

  def prepMap(width: Int, height: Int): GameMap = {
    val map = new GameMap(width, height)

    val solidRender = TerrainFlags(render = true, solid = true)
    val wallDisplay = DisplayObject('#', "#ffffff")
    val wall = Terrain(solidRender, wallDisplay)

    map.tiles(0)(0) = MapTile(wall)
    map.tiles(width-1)(height-1) = MapTile(wall)

    for(i <- 1 until width) {
      map.tiles(i)(0) = MapTile(wall)
      map.tiles(i)(height-1) = MapTile(wall)
    }

    for(i <- 1 until height) {
      map.tiles(0)(i) = MapTile(wall)
      map.tiles(width-1)(i) = MapTile(wall)
    }

    val floorDisplay = DisplayObject('.', "#ffffff")
    val floor = Terrain(TerrainFlags(render = true, solid = false), floorDisplay)

    for(x <- 1 until width-1) {
      for(y <- 1 until height-1) {
        map.tiles(x)(y) = MapTile(floor)
      }
    }
    map
  }

  def main(): Unit = {
    val map = prepMap(10, 15)

    val sprite = document.getElementById("sprite").asInstanceOf[HTMLImageElement]
    val canvas = document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]

    val renderer = new Renderer(sprite, canvas)

    renderer.clear()
    renderer.render(map)
  }

  def main(args: Array[String]) = {
    val map = prepMap(10, 15)

    println(s"${map.width} ${map.height}")

    for(x <- 0 until map.width) {
      for(y <- 0 until map.height) {
        val char = map.tiles(x)(y).terrain.display.char
        print(char)
      }
      println()
    }
  }
}
