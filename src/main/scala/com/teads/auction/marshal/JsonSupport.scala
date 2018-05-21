package com.teads.auction.marshal

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.teads.auction.actors.AuctionActor._
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol{
  implicit val lotteryWinnerM: RootJsonFormat[Winner] = jsonFormat2(Winner)
  implicit val lotteryBidsM: RootJsonFormat[LotteryBids] = jsonFormat2(LotteryBids)
  implicit val lotteryErrorM: RootJsonFormat[LotteryError] = jsonFormat1(LotteryError)
  implicit val lotteryM: RootJsonFormat[Lottery] = jsonFormat1(Lottery)
  implicit val AuctionMessageM: RootJsonFormat[AuctionMessage] = jsonFormat1(AuctionMessage)

}
