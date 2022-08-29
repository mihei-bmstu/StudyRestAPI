package repositories

import models.Category
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatest.{Assertion, BeforeAndAfterAll}
import org.scalatest.matchers.must.Matchers
import repositories.CategoryRepository
import services.{ConfigService, FlywayService, PostgresService}
import helpers.CategorySpecHelper

import scala.concurrent.Future

class CategoryRepositorySpec extends AsyncWordSpec
  with Matchers
  with BeforeAndAfterAll
  with ConfigService {

  val flywayService = new FlywayService(jdbcUrl, dbUser, dbPassword)
  val databaseService = new PostgresService(jdbcUrl, dbUser, dbPassword)
  val categoryRepository = new CategoryRepository(databaseService)

  val category: Category = Category(None, "Test category")

  override def beforeAll: Unit = {
    flywayService.migrateDatabase
  }

  override def afterAll: Unit = {
    flywayService.dropDatabase
  }

  def createAndDelete(category: Category = category)(assertion: Category => Future[Assertion]): Future[Assertion] = {
    categoryRepository.create(category) flatMap {c =>
      val assertions = assertion(c)
      categoryRepository.delete(c.id.get) flatMap {_ => assertions}
    }
  }

  "A CategoryRepository" must {

    "be empty at the beginning" in {
      categoryRepository.all map { cs => cs.size mustBe 0 }
    }

    "create valid categories" in {
      createAndDelete() { c =>
        c.id mustBe defined
        categoryRepository.all map { cs => cs.size mustBe 1 }
      }
    }

    "not find a category by title if it doesn't exist" in {
      categoryRepository.findByTitle("not a valid title") map { c => c must not be defined }
      }

/*    "find a category by title if it exists" in {
      categorySpecHelper
    }*/
  }

}
