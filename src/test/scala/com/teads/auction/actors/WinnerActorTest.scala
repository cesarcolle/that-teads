package com.teads.auction.actors
import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import com.teads.auction.actors.LotteryActor.{LotteryAuctions, LotteryBids}
import com.teads.auction.actors.WinnerActor.{Winner, Winners}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class WinnerActorTest() extends TestKit(ActorSystem("MySpec")) with ImplicitSender
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

  "A winnerLotteryActor" must {

    "Send bids to this actor " in {

      val winner = system.actorOf(WinnerActor.winnerActor)

      winner ! LotteryAuctions(bids, 4)

      expectMsg(Winners(Winner("test1", 5), Winner("test2", 6)))
    }

  }
}