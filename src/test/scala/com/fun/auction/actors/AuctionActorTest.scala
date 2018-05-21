package com.fun.auction.actors

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import AuctionActor._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}


class AuctionActorTest() extends TestKit(ActorSystem("MySpec")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A auctionActor " must {

    "Save a bid " in {

      val winner = system.actorOf(Props[AuctionActor])
      winner ! AuctionBids("test", List(1, 2, 3))

      expectMsg(AuctionMessage("bids gracefully saved test"))
    }
    "make an auction with " in {

      val winner = system.actorOf(Props[AuctionActor])

      winner ! AuctionBids("test", List(4, 5))
      expectMsg(AuctionMessage("bids gracefully saved test"))

      winner ! AuctionBids("test2", List(6))
      expectMsg(AuctionMessage("bids gracefully saved test2"))

      winner ! Auction(4)
      expectMsg(Winner("test2", 5))
    }

  }
}