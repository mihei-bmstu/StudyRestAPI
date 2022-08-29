package services

import slick.jdbc.{PostgresProfile, JdbcProfile}
import slick.jdbc.PostgresProfile.api._

class PostgresService(jdbcUrl: String, dbUser: String, dbPassword: String) extends DatabaseService {

  val driver: JdbcProfile = PostgresProfile
  val db: Database = Database.forURL(jdbcUrl, dbUser, dbPassword)
  db.createSession()
}
