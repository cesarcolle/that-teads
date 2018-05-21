package com.teads.auction.auction

import com.teads.auction.actors.AuctionActor.AuctionBids
import org.scalatest.{BeforeAndAfter, FunSuite}

class AuctionHouseTest extends FunSuite with AuctionHouse with BeforeAndAfter {

  before(reset())

  test("testAuction : simple case") {

    addBid(AuctionBids("test", List(1, 2)))
    addBid(AuctionBids("test2", List(3, 4)))
    addBid(AuctionBids("test4", List(2)))

    val w = auction(1)
    assert(w.name == "test2" && w.amount == 2)
  }

  test("auction with single bids..") {

    addBid(AuctionBids("hey", List(1)))
    val w = auction(1)
    assert(w.name == "hey")
  }

  test("random winner test") {
    addBid(AuctionBids("cesar", List(3, 4)))
    addBid(AuctionBids("daniel", List(3, 4)))

    val w = auction(3)

    assert(w.amount == 4)
  }


}
