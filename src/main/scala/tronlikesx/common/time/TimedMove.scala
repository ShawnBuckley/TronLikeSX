package tronlikesx.common.time

case class TimedMove(time: Int, move: () => Boolean)
