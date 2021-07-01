package sandbox.cats2.simulacrum.generated

import scala.annotation.{Annotation, StaticAnnotation}

object scalaVersionSpecific {

  /**
   * a trick to suppress unused import warning for this object
   */
  class suppressUnusedImportWarningForScalaVersionSpecific extends Annotation with StaticAnnotation

  implicit class iterableOnceExtension[A](private val io: IterableOnce[A]) extends AnyVal {
    def reduceOption(f: (A, A) => A): Option[A] = io.iterator.reduceOption(f)
  }
}
