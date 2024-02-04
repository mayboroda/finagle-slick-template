package tech.mayboroda
package server

import com.twitter.finagle.http.{HttpMuxer, Method}
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Future}
import org.slf4j.LoggerFactory
import tech.mayboroda.controller.{Controller, IndexController}

object Server extends App {

  val routes = Controller.all(IndexController.index)
  val routingService = RoutingService.byMethodAndPath {
    case (Method.Get, "/") => IndexController.index.route.handler
  }
  val server = Http.serve(":8080", routingService)
  Await.ready(server)

}
