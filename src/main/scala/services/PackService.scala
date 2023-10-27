package services

import cats.effect.IO
import com.typesafe.scalalogging.StrictLogging
import connectors.MtgApiConnector
import models.MtgPackRequest
import models.external.BoosterPackResponse

object PackService {

  def apply(mtgApiConnector: MtgApiConnector): PackService = new PackService with StrictLogging {

    override def generateBoosterPack(mtgPackRequest: MtgPackRequest): IO[BoosterPackResponse] =
      mtgApiConnector.generateBoosterPack(mtgPackRequest.boosterId)
  }
}

trait PackService {
  def generateBoosterPack(mtgPackRequest: MtgPackRequest): IO[BoosterPackResponse]
}
