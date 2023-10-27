package models

import cats.effect.IO
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

case class MtgPackRequest(boosterId: String, numOfPacks: Int)

object MtgPackRequest {
  implicit val encoder: Encoder[MtgPackRequest]                 = deriveEncoder[MtgPackRequest]
  implicit val decoder: Decoder[MtgPackRequest]                 = deriveDecoder[MtgPackRequest]
  implicit val entityEncoder: EntityDecoder[IO, MtgPackRequest] = jsonOf[IO, MtgPackRequest]
  implicit val entityDecoder: EntityEncoder[IO, MtgPackRequest] = jsonEncoderOf[IO, MtgPackRequest]
}
