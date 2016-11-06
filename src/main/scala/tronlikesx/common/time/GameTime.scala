package tronlikesx.common.time

import scala.collection.mutable.ListBuffer

class GameTime(var realTime: Boolean, timer: SystemTimer) {
  val timeObjects = new ListBuffer[TimeObject]

  setTimer()

  def link(timeObject: TimeObject) =
    timeObjects += timeObject

  def unlink(timeObject: TimeObject) =
    timeObjects -= timeObject

  def tick(time: Int): Unit =
    timeObjects.foreach(timeObject => timeObject.tick(time))

  def toggleRealTime(): Unit = {
    realTime = !realTime
    setTimer()
  }

  private def setTimer(): Unit = {
    if(realTime)
      timer.set(() => tick(1000), 50.0)
    else
      timer.cancel()
  }
}
