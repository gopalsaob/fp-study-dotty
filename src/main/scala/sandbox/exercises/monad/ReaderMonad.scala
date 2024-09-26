//package sandbox.exercises.monad
//
//import cats.data.*
//
//final case class Db(
//                     usernames: Map[Int, String],
//                     passwords: Map[String, String]
//                   )
//
//object ReaderMonad {
//
//  type DbReader[A] = Reader[Db, A]
//
//  def findUserName(userId: Int): DbReader[Option[String]] =
//    Reader(db => db.usernames.get(userId))
//
//  def checkPassword(username: String, v password: String): DbReader[Boolean] =
//    Reader(db => db.passwords.get(username).contains(password)
//  )
//
//  def checkLogin(userId: Int, password: String): DbReader[Boolean] = {
//    import cats.syntax.applicative.*
//    findUserName(userId).flatMap(userNameOpt =>
//      userNameOpt.map(un => checkPassword(un, password)).getOrElse(false.pure[DbReader]))
//  }
//
//}
