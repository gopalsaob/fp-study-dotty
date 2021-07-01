//package sandbox.externalexercises.cooltaxi
//
//// https://medium.com/@shashankbaravani/understanding-monoids-using-real-life-examples-6ec3cb349f2f
//object CoolTaxiModel {
//  class User(val firstName: String, val lastName: String, val custType: String)
//
//  class Location(val latitue: Double = 0.00, val longitude: Double = 0.00)
//
//  class Cab(val typ: String, val totalSeats: Int, val seatsAvlbl: Int,
//            val currentLocation: Location, val finalDestination: Location) {
//    def distanceFromHere(here: Location): Double = 0.00
//
//    def timeToDrop(here: Location): Long = 0
//
//    def isOnPromotion: Boolean = false
//
//  }
//
//  class Source() extends Location
//
//  class Destination() extends Location
//
//  //encapsulated a ride request
//  class RideRequest(val source: Source, val destination: Destination, val service: String, val seats: Int)
//}
