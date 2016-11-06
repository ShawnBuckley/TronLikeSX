package tronlikesx.common.time

import scala.collection.mutable.ListBuffer

class GameTime(var realTime: Boolean) {
  val timeObjects = new ListBuffer[TimeObject]

  def link(timeObject: TimeObject) =
    timeObjects += timeObject

  def unlink(timeObject: TimeObject) =
    timeObjects -= timeObject

  def tick(time: Int): Unit =
    timeObjects.foreach(timeObject => timeObject.tick(time))

  def toggleRealTime(): Unit =
    realTime = !realTime
}
