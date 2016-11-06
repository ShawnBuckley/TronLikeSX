package tronlikesx.common.time

trait SystemTimer {
  def set(callback: () => Unit, rate: Double): Unit
  def cancel(): Unit
}
