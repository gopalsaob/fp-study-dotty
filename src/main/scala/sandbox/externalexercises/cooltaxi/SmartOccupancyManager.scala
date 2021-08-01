//package sandbox.externalexercises.cooltaxi
//
//object SmartOccupancyManager {
//
//  import CabService.*
//  import sandbox.externalexercises.cooltaxi.CoolTaxiModel.*
//
//  /*  a non trivial process of fetching cabs from, say, in memory DB based on source and destination */
//  val cabsFromDB = List[Cab]()
//
//  def getCabs(start:Source, end: Destination, user: User, request: RideRequest): List[Cab] = {
//    (request.service, request.seats) match {
//      case ("ShareMyCab", seats) => matchPool(start, end)(seats)(cabsFromDB)
//      case ("OffersOnWheels", _) => matchOffersOnWheels(start, end)(cabsFromDB)
//      case ("MyDailyCommute", _) => matchMyDailyCommute(start, end)(cabsFromDB)
//      case _ => cabsFromDB
//    }
//  }
//
//}
