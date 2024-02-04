package tech.mayboroda
package controller

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Method, Response, Route, RouteIndex, Status}
import com.twitter.io.Buf
import com.twitter.util.Future
import org.slf4j.LoggerFactory


import scala.io.Source
import scala.util.Using

object IndexController {
  private val logger = LoggerFactory.getLogger(getClass)

  val index = new Controller {
    override def route: Route = Route("/", Service.mk {_ =>
      logger.info("Hello, index")
          val ins = getClass.getClassLoader.getResource("htmx/index.html")
          val content = Using.resource(Source.fromFile(ins.toURI)) {resource =>
            Buf.ByteArray.Owned(resource.map(_.toByte).toArray)
          }

          val response = Response(Status.Ok)
          response.setContentType("text/html; charset=UTF-8")
          response.content = content
          Future.value(response)
    }, Some(RouteIndex(alias = "index", group = "html", method = Method.Post)))
  }
}
