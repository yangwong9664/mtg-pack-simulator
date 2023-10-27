package connectors
import cats.effect.IO
import com.typesafe.config.Config
import models.external.{BoosterPackResponse, ScryfallCard}
import org.http4s.client.Client

import java.net.URLEncoder

class MtgApiConnector(client: Client[IO], config: Config) {

  def generateBoosterPack(boosterId: String): IO[BoosterPackResponse] = {
    val request = client.expect[BoosterPackResponse](s"${config.getString("mtg")}/sets/$boosterId/booster")
    request
  }

  def getRandomCard(rarity: String, set: String): IO[ScryfallCard] = {
    val uri     = "?q=" + URLEncoder.encode(s"rarity:$rarity e:$set", "UTF-8")
    val request = client.expect[ScryfallCard](s"${config.getString("mtg")}/cards/random$uri")
    request
  }
}

object MtgApiConnector {
  def apply(client: Client[IO], config: Config) = new MtgApiConnector(client, config)
}
