package com.teads.auction.actors

import akka.actor.Actor

object LotteryActor {

  case class LotteryBids(name :  String, numbers : Set[Int])

  case class Lottery()
}


class LotteryActor extends Actor{

  import LotteryActor._

  var bids = List.empty[LotteryBids]

  override def receive: Receive = {
    case lottery : LotteryBids =>
      bids = lottery :: bids

    case Lottery =>
      // algorithm here
  }
}
