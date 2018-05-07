package com.teads.auction.route

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.stream.ActorMaterializer
import com.teads.auction.actors.LotteryActor
import com.teads.auction.marshal.JsonSupport
import akka.http.scaladsl.server.Directives._
import akka.util.Timeout
import com.teads.auction.actors.LotteryActor.{Lottery, LotteryBids, LotteryError}
import akka.pattern.ask
import com.teads.auction.actors.WinnerActor.Winners




trait LotteryRoute extends JsonSupport{

  implicit def system: ActorSystem
  val lotteryActor: ActorRef = system.actorOf(Props[LotteryActor])
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
          val answer = (lotteryActor ? Lottery(price)).mapTo[Winners]
          complete(answer)
        }

      }
    }
  )


}
