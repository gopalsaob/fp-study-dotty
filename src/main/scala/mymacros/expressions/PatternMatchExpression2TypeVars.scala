//package mymacros.expressions
//
//"""
//  |
//  |def fuseMapCode(x: Expr[List[Int]]): Expr[List[Int]] =
//  |  x match
//  |    case '{ ($ls: List[t]).map[u]($f).map[Int]($g) } =>
//  |      '{ $ls.map($g.compose($f)) }
//  |...
//  |
//  |Usage:
//  |fuseMapCode('{ List(1.2).map(f).map(g) }) //'{ List(1.2).map(g.compose(f)) }
//  |fuseMapCode('{ List('a').map(h).map(i) }) //'{ List('a').map(i.compose(h)) }
//  |
//  |""".stripMargin
//object PatternMatchExpression2TypeVars {
//
//}
