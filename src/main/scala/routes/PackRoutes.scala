package routes
import cats.effect.IO
import com.typesafe.scalalogging.StrictLogging
import models.MtgPackRequest
import models.external.ScryfallCard._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import services.PackService

object PackRoutes extends StrictLogging {

  def apply(packService: PackService): HttpRoutes[IO] = HttpRoutes.of[IO] {
    case req @ POST -> Root / "generate" =>
      for {
        _              <- IO(logger.info(s"Received new pack request."))
        mtgPackRequest <- req.as[MtgPackRequest]
        result         <- packService.getRandomCard("mythic", "one")
        response       <- Ok(result)
      } yield response
  }

}
