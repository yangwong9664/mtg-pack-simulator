package connectors

import cats.effect.IO
import com.typesafe.config.Config
import models.external.BoosterPackResponse
import org.http4s.client.Client

class MtgApiConnector(client: Client[IO], config: Config) {

  def generateBoosterPack(boosterId: String): IO[BoosterPackResponse] = {
    val request = client.expect[BoosterPackResponse](s"${config.getString("mtg")}/sets/$boosterId/booster")
    request
  }
}

object MtgApiConnector {
  def apply(client: Client[IO], config: Config) = new MtgApiConnector(client, config)
}
