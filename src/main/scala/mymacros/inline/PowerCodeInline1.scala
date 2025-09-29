package mymacros.inline

object PowerCodeInline1 {

//  inline def powerMacro(x: Double, n: Int): Double =
//    ${ power('x, 'n) }

  inline def power(x: Double, n: Int): Double =
    if n == 0 then 1.0
    else if n == 1 then x
    else if n % 2 == 1 then x * power(x, n - 1)
    else power(x * x, n / 2)

}
