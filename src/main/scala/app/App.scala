package app

import cats.effect.IO
import com.typesafe.config.Config
import connectors.MtgApiConnector
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.client.Client
import org.http4s.implicits._
import routes.PackRoutes
import services.PackService

trait App {

  def config: Config
  def client: Client[IO]
  val mtgApiConnector: MtgApiConnector = MtgApiConnector(client, config)
  val packService: PackService         = PackService(mtgApiConnector)
  def routes: HttpRoutes[IO]           = PackRoutes(packService)

  def localServer(service: HttpRoutes[IO], port: Int): BlazeServerBuilder[IO] =
    BlazeServerBuilder[IO]
      .bindHttp(port, "0.0.0.0")
      .withHttpApp(service.orNotFound)
}
