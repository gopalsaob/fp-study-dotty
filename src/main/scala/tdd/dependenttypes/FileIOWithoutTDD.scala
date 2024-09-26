//package tdd.dependenttypes
//
//import scala.io._
//
//trait File {
//  val name: String
//  def open: Unit
//  def readFile: String
//  def close: Unit
//}
//
//class FileImpl(val name: String) extends File {
//  override def open(): Unit = ???
//
//  override def readFile: String = ???
//
//  override def close(): Unit = ???
//}
//
//object FileIOWithoutTDD {
//
//  def read(file: File): String = {
//    file.open()
//    val s = file.readFile()
//    file.close()
//    s
//  }
//}
