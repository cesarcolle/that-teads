package com.teads.auction.actors

import akka.actor.{Actor, Props}
import com.teads.auction.actors.LotteryActor.LotteryAuctions
import com.teads.auction.actors.WinnerActor.{Winner, Winners}
import com.teads.auction.util.LotteryAlgorithm

object WinnerActor {

  case class Winner(name: String, amount: Int)

  case class Winners(winningPrice: Winner, winnerPerson: Winner)

  val winnerActor = Props[WinnerActor]
}

  /**
  * Ok it's statefull ...
  **/
class WinnerActor extends Actor {

  override def receive: Receive = {

    case lottery: LotteryAuctions =>
      // O(nln(n))
      val resultLottery = LotteryAlgorithm.winner(lottery.bids, lottery.price)
      val price = Winner(resultLottery("winning_price")._1, resultLottery("winning_price")._2)
      val winner = Winner(resultLottery("winner")._1, resultLottery("winner")._2)
      sender() ! Winners(price, winner)
  }
}
