package tronlikesx.client.standalone

import org.scalajs.dom.window
import rlsx.map.Map
import rlsx.time.SystemTimer

class Timer()(implicit renderer: Renderer) extends SystemTimer {
  var id = 0

  override def set(callback: () => Unit, rate: Double): Unit = {
    id = window.setInterval(() => {
      callback()
      renderer.render()
    }, rate)
  }

  override def cancel(): Unit =
    if(id != 0) window.clearInterval(id)
}
