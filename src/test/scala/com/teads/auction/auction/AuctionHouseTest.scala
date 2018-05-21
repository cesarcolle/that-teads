package com.teads.auction.auction

import com.teads.auction.actors.AuctionActor.LotteryBids
import org.scalatest.{BeforeAndAfterAll, FunSuite}

class AuctionHouseTest extends FunSuite with AuctionHouse with BeforeAndAfterAll {




  test("testAuction : simple case") {
    addBid(LotteryBids("test", List(1, 2)))
    addBid(LotteryBids("test2", List(3, 4)))
    addBid(LotteryBids("test4", List(2)))

    val w = auction()
    expec


  }

}
