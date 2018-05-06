package com.teads.auction.util
import scala.collection.mutable



object LotteryAlgorithm {


  private def cover(solution: Map[Int, List[String]], input : Map[String, List[Int]], result : List[String]): List[String] = {
    if (input.size < 1)
      return result


    else {
      var tmp = List.empty[String]
      val c = solution.keySet.min(Ordering.by( key => solution(key).size))
      for(x <- solution(c)){
        tmp = x :: tmp
        solution
      }
    }
  null
  }


  private def select(solution : Map[Int, List[String]], input : Map[String, List[Int]], r : Int): Unit ={
    var cols = List.empty[Int]
    for (j <- input(r)) {
      for (i <- solution(j)){
        for (k <- input(i)){

          if (k != j){
            solution(k) = solution(k).drop(k)
          }
        }
      }
      cols += solution(j).head
    }
    return cols

  }



}
