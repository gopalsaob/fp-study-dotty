//package sandbox.externalexercises.cooltaxi
//
//object CabService {
//  import sandbox.externalexercises.cooltaxi.CoolTaxiModel.*
//  import CabMorphisms.*
//
//  val MAX_DIST = 4.00
//  val MAX_TIME_WAIT = 60
//
//  /* predfined recipe for implementing abstract notion of convinience; */
//  def matchConvinience(location: Location) = matchDistanceToPickUp(location, MAX_DIST) andThen matchTimeToDrop(location, MAX_TIME_WAIT)
//
//  /* more coarse grained filters have been defined based on underlying ones; */
//  def matchBasic(start:Source, end: Destination) = matchRoutes(start, end) andThen matchClass("basic")
//  def matchPool(start:Source, end: Destination)(seats: Int) = matchRoutes(start, end) andThen matchClass("pool") andThen matchSeats(seats)
//  def matchPremium(start:Source, end: Destination) = matchRoutes(start, end) andThen matchClass("premium")
//
//  /* more abtstract constructs defined by business needs however the client is not aware of
// how these constructs are composed to generate final outcomes */
//  def matchMyDailyCommute(start:Source, end: Destination) = matchPool(start, end)(1) andThen matchConvinience(start)
//  def matchOffersOnWheels(start:Source, end: Destination) = matchPool(start, end)(1) andThen matchPromotionCabs
//
//}
