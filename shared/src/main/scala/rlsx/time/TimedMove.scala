package rlsx.time

case class TimedMove(time: Int, move: () => Boolean)
