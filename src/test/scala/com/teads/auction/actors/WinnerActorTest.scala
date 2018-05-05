package com.teads.auction.actors
import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import com.teads.auction.actors.LotteryActor.LotteryBids
import com.teads.auction.actors.WinnerActor.LotteryComputing
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class WinnerActorTest() extends TestKit(ActorSystem("MySpec")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  var bids = List.empty[LotteryBids]

  override def beforeAll(){
    bids = LotteryBids("test", Set(1, 2, 3)) :: bids
    bids = LotteryBids("test1", Set(1, 4, 5)) :: bids
    bids = LotteryBids("test2", Set(1, 4, 6)) :: bids
  }

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A winnerLotteryActor" must {
    "Send bids to this actor " in {
      val winner = system.actorOf(WinnerActor.winnerActor)
      winner ! LotteryComputing(bids, List(1, 2, 6))

    }

  }
}