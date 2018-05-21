package com.teads.auction.actors
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.teads.auction.actors.AuctionActor._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}


class AuctionActorTest() extends TestKit(ActorSystem("MySpec")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A auctionActor " must {

    "Save a bid " in {

      val winner = system.actorOf(Props[AuctionActor])
      winner !  LotteryBids("test", List(1, 2, 3))

      expectMsg(AuctionMessage("bids gracefully saved test"))
    }
    "make an auction with " in {

      val winner = system.actorOf(Props[AuctionActor])

      winner !  LotteryBids("test", List(4, 5))
      expectMsg(AuctionMessage("bids gracefully saved test"))

      winner ! LotteryBids("test2", List(6))
      expectMsg(AuctionMessage("bids gracefully saved test2"))

      winner ! Lottery(4)
      expectMsg(Winner("test2", 5))
    }

  }
}