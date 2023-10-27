import app.App
import cats.effect.{ExitCode, IO, IOApp, Resource}
import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.StrictLogging
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.client.{Client => HttpClient}

import scala.concurrent.duration.DurationInt

object Main extends IOApp with StrictLogging {

  val config = IO(ConfigFactory.load())

  override def run(args: List[String]): IO[ExitCode] =
    (for {
      conf       <- Resource.eval(config)
      httpClient <- BlazeClientBuilder[IO].withResponseHeaderTimeout(2.minutes).withIdleTimeout(1.minute).resource
      app = new App {
        override def config: Config         = conf
        override def client: HttpClient[IO] = httpClient
      }
      _ <- app
        .localServer(app.routes, conf.getInt("port"))
        .resource
    } yield ()).use(_ => IO.never *> IO(ExitCode.Success))
}
