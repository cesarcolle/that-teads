package com.fun.auction.actors

import akka.actor.Actor
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.fun.auction.auction.AuctionHouse
import com.teads.auction.auction.AuctionHouse

import scala.concurrent.duration._


object AuctionActor {

  case class AuctionBids(name: String, numbers: List[Int])

  case class Auction(price: Int)

  case class LotteryError(msg: String)

  case class Winner(name: String, amount: Int)

  case class AuctionMessage(msg: String)


}

class AuctionActor extends Actor with AuctionHouse {

  import AuctionActor._

  val materializer = ActorMaterializer()

  implicit val timeout: Timeout = Timeout(5 seconds)

  override def receive: Receive = {


    case lottery: AuctionBids =>
      addBid(lottery)
      sender() ! AuctionMessage("bids gracefully saved " + lottery.name)

    case Auction(price) =>
      sender ! auction(price)
  }

}
