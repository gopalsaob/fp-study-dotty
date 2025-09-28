package mymacros.inline

import scala.compiletime.summonFrom
import scala.collection.immutable.{HashSet, TreeSet}

object InlineSummoning {

  inline def setFor[T]: Set[T] =
    summonFrom {
      case ord: Ordering[T] => new TreeSet[T]
      case _ => new HashSet[T]
    }

}
