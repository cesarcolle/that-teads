package com.fun.auction.marshal

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.fun.auction.actors.AuctionActor._
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val lotteryWinnerM: RootJsonFormat[Winner] = jsonFormat2(Winner)
  implicit val lotteryBidsM: RootJsonFormat[AuctionBids] = jsonFormat2(AuctionBids)
  implicit val lotteryErrorM: RootJsonFormat[LotteryError] = jsonFormat1(LotteryError)
  implicit val lotteryM: RootJsonFormat[Auction] = jsonFormat1(Auction)
  implicit val AuctionMessageM: RootJsonFormat[AuctionMessage] = jsonFormat1(AuctionMessage)

}
