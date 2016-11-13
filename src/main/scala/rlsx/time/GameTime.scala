package rlsx.time

import rlsx.Actor.Actor
import tronlikesx.client.common.Renderer

import scala.collection.mutable.ListBuffer

class GameTime(var realTime: Boolean, timer: SystemTimer)(implicit renderer: Renderer) {
  val timeObjects = new ListBuffer[TimeObject]
  val actors = new ListBuffer[Actor]

  setTimer()

  def link(timeObject: TimeObject) =
    timeObjects += timeObject

  def unlink(timeObject: TimeObject) =
    timeObjects -= timeObject

  def link(actor: Actor) =
    actors += actor

  def unlink(actor: Actor) =
    actors -= actor

  def next(): Unit = {
    while(actors.forall(_.next.nonEmpty))
      tick(actors.min(Ordering.by((actor: Actor) => actor.next.get.time)).next.get.time)
    renderer.render()
  }

  def tick(time: Int): Unit = {
    actors.foreach(_.tick(time))
    timeObjects.foreach(_.tick(time))
  }

  def toggleRealTime(): Unit = {
    realTime = !realTime
    setTimer()
  }

  private def setTimer(): Unit = {
    if(realTime) {
      timer.set(() => {
        actors.foreach(move => {
          move.next
          move.pop().fold()(_.move())
        })
        tick(1000)
      }, 50.0)
    } else
      timer.cancel()
  }
}
