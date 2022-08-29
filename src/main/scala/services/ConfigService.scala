package services

import com.typesafe.config.ConfigFactory

trait ConfigService {

  private val config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")
  private val databaseConfig = config.getConfig("database")

  val httpHost: String = httpConfig.getString("interface")
  val httpPort: String = httpConfig.getString("port")

  val jdbcUrl: String = databaseConfig.getString("url")
  val dbUser: String = databaseConfig.getString("user")
  val dbPassword: String = databaseConfig.getString("password")

}
