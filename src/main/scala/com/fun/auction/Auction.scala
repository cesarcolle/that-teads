package com.fun.auction

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.fun.auction.route.AuctionRoute

import scala.concurrent.Await
import scala.concurrent.duration._


object Auction extends AuctionRoute {

  val routes: Route = auctionRoute

  implicit val materializer: ActorMaterializer = ActorMaterializer()

  override implicit def system: ActorSystem = ActorSystem()

  override implicit val timeout: Timeout = Timeout(5 seconds) // needed for `?` below

  def main(args: Array[String]) {
    Http().bindAndHandle(routes, "localhost", 8080)
    println("server started...")
    Await.result(system.whenTerminated, Duration.Inf)
  }


}
