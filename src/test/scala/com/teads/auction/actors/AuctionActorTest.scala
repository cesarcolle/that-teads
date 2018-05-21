package com.teads.auction.actors
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.teads.auction.actors.AuctionActor._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}


class AuctionActorTest() extends TestKit(ActorSystem("MySpec")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  var bids = List.empty[LotteryBids]

  override def beforeAll(){
    bids = LotteryBids("test", List(1, 2, 3)) :: bids
    bids = LotteryBids("test1", List( 4, 5)) :: bids
    bids = LotteryBids("test2", List(6)) :: bids

  }

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A auctionActor " must {

    "Save a bid " in {

      val winner = system.actorOf(Props[AuctionActor])
      winner ! bids.head

      expectMsg(AuctionMessage("bids gracefully saved"))

    }

    "make an auction with " in {

      val winner = system.actorOf(Props[AuctionActor])

      winner ! bids(1)
      expectMsg(AuctionMessage("bids gracefully saved"))

      winner ! bids(2)
      expectMsg(AuctionMessage("bids gracefully saved"))

      winner ! Lottery(4)
      expectMsg(Winner("test2", 5))
    }

  }
}