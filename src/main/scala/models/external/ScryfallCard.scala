package models.external

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

case class ImageUris(small: String)

object ImageUris {
  implicit val encoder: Encoder[ImageUris]                 = deriveEncoder[ImageUris]
  implicit val decoder: Decoder[ImageUris]                 = deriveDecoder[ImageUris]
  implicit val entityEncoder: EntityDecoder[IO, ImageUris] = jsonOf[IO, ImageUris]
  implicit val entityDecoder: EntityEncoder[IO, ImageUris] = jsonEncoderOf[IO, ImageUris]
}

case class Prices(usd: String, usd_foil: Option[String])

object Prices {
  implicit val encoder: Encoder[Prices]                 = deriveEncoder[Prices]
  implicit val decoder: Decoder[Prices]                 = deriveDecoder[Prices]
  implicit val entityEncoder: EntityDecoder[IO, Prices] = jsonOf[IO, Prices]
  implicit val entityDecoder: EntityEncoder[IO, Prices] = jsonEncoderOf[IO, Prices]
}

case class ScryfallCard(name: String, multiverse_ids: Seq[Int], prices: Prices, image_uris: ImageUris, rarity: String)

object ScryfallCard {
  implicit val encoder: Encoder[ScryfallCard]                 = deriveEncoder[ScryfallCard]
  implicit val decoder: Decoder[ScryfallCard]                 = deriveDecoder[ScryfallCard]
  implicit val entityEncoder: EntityDecoder[IO, ScryfallCard] = jsonOf[IO, ScryfallCard]
  implicit val entityDecoder: EntityEncoder[IO, ScryfallCard] = jsonEncoderOf[IO, ScryfallCard]
}
