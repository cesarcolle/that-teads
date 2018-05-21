package com.teads.auction.actors

import akka.actor.{Actor, ActorRef}
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.teads.auction.auction.AuctionHouse

import scala.concurrent.duration._


object AuctionActor {

  case class LotteryBids(name: String, numbers: List[Int])

  case class LotteryAuctions(bids: List[LotteryBids], price: Int)

  case class Lottery(price: Int)

  case class LotteryError(msg: String)

  case class Winner(name: String, amount: Int)

  case class AuctionMessage(msg : String)


}

class AuctionActor extends Actor with AuctionHouse {

  import AuctionActor._

  val materializer = ActorMaterializer()

  implicit val timeout: Timeout = Timeout(5 seconds)

  override def receive: Receive = {


    case lottery: LotteryBids =>
      addBid(lottery)
      sender() ! AuctionMessage("bids gracefully saved")

    case Lottery(price) =>
      sender ! auction(price)
  }

}
