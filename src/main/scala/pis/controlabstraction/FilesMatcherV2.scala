package pis.controlabstraction

object FilesMatcherV2 {

  private def filesHere = (new java.io.File(".")).listFiles

  def filesEnding(query: String) =
    filesMatching(query, _.endsWith(_))

  def filesContaining(query: String) =
    filesMatching(query, _.contains(_))

  def filesRegex(query: String) =
    filesMatching(query, _.matches(_))

  private def filesMatching(query: String, matcher: (String, String) => Boolean) =
    for file <- filesHere if matcher(file.getName, query)
    yield file
}
