package com.teads.auction.marshal

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.teads.auction.actors.LotteryActor.{Lottery, LotteryBids, LotteryError}
import com.teads.auction.actors.WinnerActor.{Winner, Winners}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol{
  implicit val lotteryWinnerM: RootJsonFormat[Winner] = jsonFormat2(Winner)
  implicit val lotteryWinnersM: RootJsonFormat[Winners] = jsonFormat2(Winners)
  implicit val lotteryBidsM: RootJsonFormat[LotteryBids] = jsonFormat2(LotteryBids)
  implicit val lotteryErrorM: RootJsonFormat[LotteryError] = jsonFormat1(LotteryError)
  implicit val lotteryM: RootJsonFormat[Lottery] = jsonFormat1(Lottery)

}
