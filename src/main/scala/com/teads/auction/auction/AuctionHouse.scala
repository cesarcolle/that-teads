package com.teads.auction.auction

import com.teads.auction.actors.AuctionActor.{LotteryBids, Winner}

import scala.util.Random

trait AuctionHouse {
  private var bids : Array[LotteryBids] = Array.empty[LotteryBids]
  private var _price : Int = 0



  def auction() : Winner = {
    var flatBid = bids.flatMap(bid => bid.numbers.map(number => (bid.name, number)))

    flatBid = flatBid.sortBy(_._2)
    // delete the price beside the initial price
    flatBid = flatBid.filter(_._2 >= _price)
    var bestPrice = flatBid.lastOption.getOrElse(throw new RuntimeException("can't make empty lottery or with auction beside initial price"))
    val winners = flatBid.filter(_._2 >= bestPrice._2 )
    val random : Int= Random.nextInt(winners.length)

    val auctionWinner = winners(random)

    val winningPrice = flatBid.filter(_._1 != auctionWinner._1).lastOption.getOrElse(auctionWinner)._2
    Winner(auctionWinner._1, winningPrice)
  }

  def reset() : Unit =  {
    bids = Array.empty[LotteryBids]
  }


  def price_ = (value:Int) => _price = value

  def addBid(lottery : LotteryBids): Unit ={
    bids =  bids :+ lottery

  }






}
