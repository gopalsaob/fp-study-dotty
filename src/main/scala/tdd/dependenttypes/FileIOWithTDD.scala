//package tdd.dependenttypes
//
//trait FileState
//
//object Empty extends FileState
//object Open extends FileState
//object Closed extends FileState
//
//trait File[S <: FileState] {
//  val name: String
//}
//
//object File {
//  def open[S <: Empty](file: File[S]): File[Open] = ???
//  def readFile[S <: Open](file: File[S]): String = ???
//  def close[S <: Open](file: File[S]): File[Closed] = ???
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
//class FileIOWithTDD {
//
//}
