package services

import cats.effect.IO
import com.typesafe.scalalogging.StrictLogging
import connectors.MtgApiConnector
import models.MtgPackRequest
import models.external.{BoosterPackResponse, ScryfallCard}
import money.GBP
import models.external.BoosterPackResponse
import models.external.Prices._
import models.external.ImageUris._
import models.external.ScryfallCard
import org.http4s.client.Client

import java.net.URLEncoder
import money._
object PackService {

  def apply(mtgApiConnector: MtgApiConnector): PackService = new PackService with StrictLogging {

    override def generateBoosterPack(mtgPackRequest: MtgPackRequest): IO[BoosterPackResponse] =
      mtgApiConnector.generateBoosterPack(mtgPackRequest.boosterId)

    override def getRandomCard(rarity: String, set: String): IO[ScryfallCard] =
      mtgApiConnector.getRandomCard(rarity, set).map(usdToPounds)
  }

  private def usdToPounds(scryfallCard: ScryfallCard): ScryfallCard = {
    def convert(usd: String) = {
      val conversion: Conversion        = Map((USD, GBP) -> 0.63)
      implicit val converter: Converter = Converter(conversion)
      val amount                        = Money(usd.toDouble, USD) to GBP

      f"${amount.amount}%.2f"
    }

    scryfallCard.copy(
      prices = scryfallCard.prices.copy(
        usd = convert(scryfallCard.prices.usd),
        usd_foil = scryfallCard.prices.usd_foil.fold(None: Option[String]) { c =>
          Some(convert(c))
        }
      )
    )
  }
}

trait PackService {
  def generateBoosterPack(mtgPackRequest: MtgPackRequest): IO[BoosterPackResponse]
  def getRandomCard(rarity: String, set: String): IO[ScryfallCard]

}
