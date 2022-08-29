import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.must.Matchers
import services.{ConfigService, FlywayService}
import helpers.CategorySpecHelper
import controllers.CategoryController
import models.{Category, CategoryJson}
import repositories.CategoryRepository
import services._

import scala.concurrent.{ExecutionContextExecutor, Future}


class CategoryEndpointSpec extends AsyncWordSpec
  with Matchers
  with BeforeAndAfterAll
  with ConfigService
  with WebAPI
  with ScalatestRouteTest
  with CategoryJson
  {
    override implicit val executor: ExecutionContextExecutor = system.dispatcher

    val flywayService = new FlywayService(jdbcUrl, dbUser, dbPassword)

    val databaseService = new PostgresService(jdbcUrl, dbUser, dbPassword)

    val categoryRepository = new CategoryRepository(databaseService)

    val categorySpecHelper = new CategorySpecHelper(categoryRepository)

    val categoryController = new CategoryController(categoryRepository)

    override def beforeAll: Unit = { flywayService.migrateDatabase }

    override def afterAll: Unit = flywayService.dropDatabase

    "A CategoryEndpoint" must {
      "return an empty list at the beginning" in {
        Get("/categories/") ~> categoryController.routes ~> check {
          status mustBe StatusCodes.OK
        }
      }
    }

}
