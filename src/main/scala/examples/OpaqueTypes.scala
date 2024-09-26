package examples

object MetricSystem {
  opaque type Meter = Double

  def meter(d: Double): Meter       = d
  def centimeters(d: Double): Meter = d / 100
}

object ImperialSystem {
  opaque type Inches = Double

  def inches(d: Double): Inches = d
  def feet(d: Double): Inches   = d * 12
}

object OpaqueTypes {

  import MetricSystem.Meter

  def orbiterCourseCorrection(n: Meter) = ???

}
