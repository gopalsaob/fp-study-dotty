package tdd.dependenttypes

case class Matrix[Row <: Int, Column <: Int](dtList: DTList[Row])
