package com.fun.auction.route

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.fun.auction.actors.AuctionActor
import com.fun.auction.actors.AuctionActor.{AuctionBids, Winner}
import com.fun.auction.marshal.JsonSupport
import com.teads.auction.actors.AuctionActor
import com.teads.auction.actors.AuctionActor.{Auction, AuctionBids, Winner}
import com.teads.auction.marshal.JsonSupport


trait AuctionRoute extends JsonSupport {

  implicit def system: ActorSystem

  val auctionActor: ActorRef = system.actorOf(Props[AuctionActor])
  private val actorMaterializer = ActorMaterializer
  implicit val timeout: Timeout

  val auctionRoute: Route = concat(
    path("addBids") {
      post {
        entity(as[AuctionBids]) { bid =>
          auctionActor ! bid
          complete("Bid added successfully")
        }
      }
    },
    path("lottery") {
      get {
        parameter('reservedPrice.as[Int]) { price =>
          val answer = (auctionActor ? Auction(price)).mapTo[Winner]
          complete(answer)
        }

      }
    }
  )


}
