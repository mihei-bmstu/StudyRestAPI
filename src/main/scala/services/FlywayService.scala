package services

import org.flywaydb.core.Flyway

class FlywayService(jdbcUrl: String, dbUser: String, dbPassword: String) {

  private val flyway = Flyway.configure().dataSource(jdbcUrl, dbUser, dbPassword).load()

  def migrateDatabase: FlywayService = {
    flyway.migrate()
    this
    }

  def dropDatabase: FlywayService = {
    flyway.clean()
    this
    }

}
