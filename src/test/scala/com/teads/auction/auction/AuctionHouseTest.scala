package com.teads.auction.auction

import com.teads.auction.actors.AuctionActor.LotteryBids
import org.scalatest.{BeforeAndAfter, FunSuite}

class AuctionHouseTest extends FunSuite with AuctionHouse with BeforeAndAfter {

  before(reset())

  test("testAuction : simple case") {

    addBid(LotteryBids("test", List(1, 2)))
    addBid(LotteryBids("test2", List(3, 4)))
    addBid(LotteryBids("test4", List(2)))

    val w = auction(1)
    assert(w.name == "test2" && w.amount == 2 )
  }

  test("auction with single bids..") {

    addBid(LotteryBids("hey", List(1)))
    val w = auction(1)
    assert(w.name == "hey")
  }

  test("random winner test") {
    addBid(LotteryBids("cesar", List(3, 4)))
    addBid(LotteryBids("daniel", List(3, 4)))

    val w = auction(3)

    assert(w.amount == 4)
  }


}
