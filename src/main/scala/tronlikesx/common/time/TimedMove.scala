package tronlikesx.common.time

case class TimedMove(time: Int, callback: () => Unit)
