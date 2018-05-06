package com.teads.auction.actors

import akka.actor.{Actor, Props}
import com.teads.auction.actors.WinnerActor.LotteryComputing

import scala.collection.mutable
import scala.collection.mutable.Map

object WinnerActor {

  case class LotteryComputing(bids: Map[String, List[Int]], solution: List[Int])

  val winnerActor = Props[WinnerActor]
}

/**
  * Ok it's statefull ...
  **/
class WinnerActor extends Actor {

  var mappingBid: mutable.Map[String, List[Int]] = mutable.Map.empty

  private def bidMapping(lotteryList: mutable.Map[String, List[Int]]): mutable.Map[Int, Set[String]] = {
    var matrix = List.empty[List[Int]]
    var mapping = Map.empty[Int, Set[String]]
    mapping.withDefaultValue(Set.empty[String])
    // long life to functional.
    for ( (name, numbers) <- lotteryList) {
      numbers.foreach(number => {
        mapping.get(number) match{
          case Some(names : Set[String]) => mapping(number) =  names + name
          case None => mapping(number) = Set(name)
        }
      })
    }
    mapping
  }

  override def receive: Receive = {

    case lottery: LotteryComputing =>
      // O(nln(n))
      var mappingLottery = bidMapping(lottery.bids)

  }
}
