package tronlikesx.client.standalone

import org.scalajs.dom.window
import rlsx.Game
import rlsx.time.SystemTimer

class Timer(renderer: Renderer) extends SystemTimer {
  var id = 0

  override def set(callback: () => Unit, rate: Double): Unit = {
    id = window.setInterval(() => {
      callback()
      renderer.clear()
      renderer.render(Game.session.map)
    }, rate)
  }

  override def cancel(): Unit =
    if(id != 0) window.clearInterval(id)
}
