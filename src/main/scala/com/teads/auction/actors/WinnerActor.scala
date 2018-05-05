package com.teads.auction.actors

import akka.actor.{Actor, Props}
import com.teads.auction.actors.LotteryActor.LotteryBids
import com.teads.auction.actors.WinnerActor.LotteryComputing

object WinnerActor {

  case class LotteryComputing(bids: List[LotteryBids], solution: List[Int])

  val winnerActor = Props[WinnerActor]
}

/**
  * Ok it's statefull ...
  **/
class WinnerActor extends Actor {

  var mappingBid: Map[String, List[Int]] = Map.empty

  private def bidMapping(mapping: List[LotteryBids], maxBid: Int): List[List[Int]] = {
    var matrix = List.empty[List[Int]]
    var mapping = Map.empty[Int, List[String]]

    for ((_, numbers) <- mapping) {
      var initialize = List.fill[Int](maxBid + 1)(0)
      println("init == " + initialize)
      matrix = initialize :: matrix
    }
    matrix
  }

  override def receive: Receive = {

    case lottery: LotteryComputing =>
      // O(nln(n))
      var adjencyMatric = bidMapping(lottery.bids, lottery.solution.max)

  }
}
