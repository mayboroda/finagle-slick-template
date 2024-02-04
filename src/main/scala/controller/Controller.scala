package tech.mayboroda
package controller

import com.twitter.finagle.http.Route

trait Controller {
  def route: Route
}

object Controller {
  def all(controllers: Controller*): Seq[Route] = controllers.map(_.route)
}
