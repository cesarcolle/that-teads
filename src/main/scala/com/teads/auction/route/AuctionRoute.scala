package com.teads.auction.route

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.teads.auction.actors.AuctionActor
import com.teads.auction.actors.AuctionActor.{Lottery, LotteryBids, Winner}
import com.teads.auction.marshal.JsonSupport




trait AuctionRoute extends JsonSupport{

  implicit def system: ActorSystem
  val lotteryActor: ActorRef = system.actorOf(Props[AuctionActor])
  private val actorMaterializer = ActorMaterializer
  implicit val timeout : Timeout

  val lotteryRoute = concat(
    path("addBids") {
      post {
        entity(as[LotteryBids]){ bid =>
          lotteryActor ! bid
          complete("Bid added successfully")
        }
      }
    },
    path("lottery"){
      get {
        parameter('reservedPrice.as[Int]) {price =>
          val answer = (lotteryActor ? Lottery(price)).mapTo[Winner]
          complete(answer)
        }

      }
    }
  )


}
