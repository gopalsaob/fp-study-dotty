package tdd

object Tuples {

  // See https://dotty.epfl.ch/api/scala/*:.html
  // and https://dotty.epfl.ch/api/scala/*:$.html

//  | val t12d4 = t12.drop(4)         // Drop leading 4 elements
//  val t12d3: (Float, (Long, Double)) = (4.4,(5,6.6))
//  val t12d4: (Long, Double) *: scala.Tuple$package.EmptyTuple.type = ((5,6.6),)
//
//  scala> val t12t3 = t12.take(3)
//  | val t12s3 = t12.splitAt(3)      // Like take and drop combined
//  | val t12s4 = t12.splitAt(4)      // Like take and drop combined
//  val t12t3: Int *: String *: Double *: EmptyTuple = (1,two,3.3)
//  val t12s3: (scala.Tuple.Take[Int *: String *: Double *: t2.type, 3],
//    scala.Tuple.Drop[Int *: String *: Double *: t2.type, 3]
//    ) = ((1,two,3.3),(4.4,(5,6.6)))
//  val t12s4: (scala.Tuple.Take[Int *: String *: Double *: t2.type, 4],
//    scala.Tuple.Drop[Int *: String *: Double *: t2.type, 4]
//    ) = ((1,two,3.3,4.4),((5,6.6),))
//
//  scala> val a  = t1.toArray             // Convert to collections
//  | val ia = t1.toIArray
//  | val l  = t1.toList
//  val a: Array[Object] = Array(1, two, 3.3)   // Least upper bound type: Object
//  val ia: opaques.IArray[Object] = Array(1, two, 3.3)
//  val l: List[Tuple.Union[t1.type]] = List(1, two, 3.3)
//
//  // See https://dotty.epfl.ch/api/scala/IArray$package$$IArray$.html
//  scala> val ta  = Tuple.fromArray(a)    // Convert from collections
//  | val tia = Tuple.fromIArray(ia)
//  val ta: Tuple = (1,two,3.3)
//  val tia: Tuple = (1,two,3.3)
//
//  scala> case class Person(name: String, age: Int)
//  | val tp = Tuple.fromProduct(Person("Dean", 29))  // Convert from Product
//  val tp: Tuple = (Dean,29)
//
//  scala> val z1 = t1.zip(t2)             // Note that t1's "3.3" is dropped
//  | val z2 = t1.zip((4.4,5,6.6))    // Two, three-element tuples zipped
//  val z1: (Int, Float) *: (String, (Long, Double)) *: EmptyTuple = ((1,4.4),(two,(5,6.6)))
//  val z2: (Int, Double) *: (String, Int) *: (Double, Double) *: EmptyTuple = ((1,4.4),(two,5),(3.3,6.6))
}

class TupleVals {
  val t1 = (1, "two", 3.3)
  val t2 = (4.4f, (5L, 6.6))

  val t01 = 0L *: t1 // Prepend an element
  val t12 = t1 ++ t2 // Concatenate tuples

  // Pattern match with the *: object:
  val zero *: one *: two *: three *: EmptyTuple = t01
  val t12d3                                     = t12.drop(3)
}
