package rlsx.time

trait TimeObject {
  def tick(time: Int): Unit
  def add(move: TimedMove): Unit
}