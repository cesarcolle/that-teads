package com.teads.auction.actors

import akka.actor.Actor
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.duration._
import akka.pattern.ask
import com.teads.auction.actors.WinnerActor.Winners

import scala.concurrent.Await


object LotteryActor {

  case class LotteryBids(name: String, numbers: List[Int])

  case class LotteryAuctions(bids: List[LotteryBids], price : Int)
  case class Lottery(price : Int)
  case class LotteryError(msg : String)

}
import scala.concurrent.ExecutionContext.Implicits.global
import akka.pattern.pipe
class LotteryActor extends Actor {

  import LotteryActor._

  val materializer = ActorMaterializer()
  val winningActor = context.actorOf(WinnerActor.winnerActor)

  var bids = List.empty[LotteryBids]
  implicit val timeout = Timeout(5 seconds) // needed for `?` below

  override def receive: Receive = {


    case lottery: LotteryBids =>
      bids = lottery :: bids

    case Lottery(price) =>
      if (bids.nonEmpty){
        val future = ask(winningActor, LotteryAuctions(bids, price)).mapTo[Winners] pipeTo sender()
        bids = List.empty[LotteryBids]
      }
  }
}
