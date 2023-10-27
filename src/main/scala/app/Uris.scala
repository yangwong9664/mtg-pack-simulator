package app

import org.http4s.Uri
import org.http4s.syntax.literals._

object Uris {
  val test = Uri(path = path"/test")
}
