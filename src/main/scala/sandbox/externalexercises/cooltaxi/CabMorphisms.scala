//package sandbox.externalexercises.cooltaxi
//
//object CabMorphisms {
//  import sandbox.externalexercises.cooltaxi.CoolTaxiModel._
//
//  sealed trait CabMorphism {
//    def m: Function1[List[Cab], List[Cab]]
//  }
//
//  object CabMorphism {
//
//    /* a simple filter to filter cabs based on source and destination, */
//    case class MatchRoutes(source: Source, destination: Destination) extends CabMorphism {
//      override def m: List[Cab] => List[Cab] = {
//        cabs: List[Cab] => cabs.filter(c => c.finalDestination == destination && isWithinRange(c.currentLocation, source))
//      }
//    }
//
//    /* a simple filter to filter cabs based on service type - pool, premier, etc, */
//    case class MatchClass(typeName: String) extends CabMorphism {
//      override def m: List[Cab] => List[Cab] = {
//        cabs: List[Cab] => cabs.filter(_.typ.equalsIgnoreCase(typeName))
//      }
//    }
//
//    /* a simple filter to filter cabs based on some kind of promotion */
//    case object MatchPromotion extends CabMorphism {
//      override def m: List[Cab] => List[Cab] = {
//        cabs: List[Cab] => cabs.filter(_.isOnPromotion)
//      }
//    }
//
//    case class MatchSeats(numOfSeats: Int) {
//
//    }
//
//    def isWithinRange(cabLoc: Location, myLoc: Location) = true
//  }
//
//  /*  a long laborious process of matching the best cabs based on multitude of factors
//  *  such as driver ratings, user ratings, co-passenger ratings, traffic conditions, etc
//  * */
//  def matchSeats(numOfSeats: Int): CabMorphism = {
//    cabs: List[Cab] => cabs.filter(_.seatsAvlbl == numOfSeats)
//  }
//
//  def matchDistanceToPickUp(here: Location, dis: Double): CabMorphism = {
//    cabs: List[Cab] => cabs.filter(_.distanceFromHere(here) == dis)
//  }
//
//  def matchTimeToDrop(here: Location, time: Int): CabMorphism = {
//    cabs: List[Cab] => cabs.filter(_.timeToDrop(here) <= time)
//  }
//}
