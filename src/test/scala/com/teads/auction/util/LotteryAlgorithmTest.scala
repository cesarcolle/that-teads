package com.teads.auction.util

import com.teads.auction.actors.LotteryActor.LotteryBids
import org.scalatest.{BeforeAndAfterAll, FunSuite}

class LotteryAlgorithmTest extends FunSuite with BeforeAndAfterAll{

  var bids : List[LotteryBids] = List.empty

  override  def beforeAll(): Unit ={
    bids = LotteryBids("test", List(1, 2, 3)) :: bids
    bids = LotteryBids("test1", List(5, 7, 8)) :: bids
    bids = LotteryBids("test2", List(9, 10, 3)) :: bids
  }

  test("testWinner") {
    val winners = LotteryAlgorithm.winner(bids, 7)

    assert(winners("winning_price")._1 == "test1")
    assert(winners("winner")._1 == "test2")

  }



}
