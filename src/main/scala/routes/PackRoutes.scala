package routes
import cats.effect.IO
import com.typesafe.scalalogging.StrictLogging
import io.circe.generic.codec.DerivedAsObjectCodec.deriveCodec
import models.MtgPackRequest
import org.http4s.HttpRoutes
import org.http4s.circe.CirceEntityCodec.circeEntityEncoder
import org.http4s.dsl.io._
import services.PackService

object PackRoutes extends StrictLogging {

  def apply(packService: PackService): HttpRoutes[IO] = HttpRoutes.of[IO] {
    case req @ POST -> Root / "generate" =>
      for {
        _              <- IO(logger.info(s"Received new pack request."))
        mtgPackRequest <- req.as[MtgPackRequest]
        result         <- packService.generateBoosterPack(mtgPackRequest)
        response       <- Ok(result)
      } yield response
  }

}
