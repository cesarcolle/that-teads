package com.teads.auction.util
import com.teads.auction.actors.LotteryActor.LotteryBids


object LotteryAlgorithm {



  def winner(bids : List[LotteryBids], price : Int): Map[String, (String, Int)] ={
    var flatBid = bids.flatMap(bid => bid.numbers.map(number => (bid.name, number)))

    flatBid = flatBid.sortBy(_._2)
    flatBid = flatBid.filter(_._2 > price)

    var winner = flatBid.last
    var winningPrice = flatBid.filter(_._1 != winner._1).last

    if (winner == null){
      winner = ("no winner", price)
    }
    if (winningPrice == null){
      winningPrice = ("no winner", price)
    }

    Map( "winner" -> winner, "winning_price" -> winningPrice)

  }




}
