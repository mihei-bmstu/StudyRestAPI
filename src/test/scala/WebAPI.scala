import akka.actor.ActorSystem
import akka.stream.Materializer

import scala.concurrent.ExecutionContext

trait WebAPI {

  implicit val system: ActorSystem
  implicit val materializer: Materializer
  implicit val executor: ExecutionContext

}
