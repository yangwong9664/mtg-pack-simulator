package models.external

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

case class BoosterPackResponse(cards: Seq[Cards])

object BoosterPackResponse {
  implicit val encoder: Encoder[BoosterPackResponse]                 = deriveEncoder[BoosterPackResponse]
  implicit val decoder: Decoder[BoosterPackResponse]                 = deriveDecoder[BoosterPackResponse]
  implicit val entityEncoder: EntityDecoder[IO, BoosterPackResponse] = jsonOf[IO, BoosterPackResponse]
  implicit val entityDecoder: EntityEncoder[IO, BoosterPackResponse] = jsonEncoderOf[IO, BoosterPackResponse]
}

case class Cards(name: String, rarity: String, imageUrl: String, multiverseid: String)

object Cards {
  implicit val encoder: Encoder[Cards]                 = deriveEncoder[Cards]
  implicit val decoder: Decoder[Cards]                 = deriveDecoder[Cards]
  implicit val entityEncoder: EntityDecoder[IO, Cards] = jsonOf[IO, Cards]
  implicit val entityDecoder: EntityEncoder[IO, Cards] = jsonEncoderOf[IO, Cards]
}
