package models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import slick.jdbc.PostgresProfile.api._

case class Category(id: Option[Long] = None, title: String)

trait CategoryJson extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val categoryFormat: RootJsonFormat[Category] = jsonFormat2(Category.apply)
}

trait CategoryTable {

  class Categories(tag: Tag) extends Table[Category](tag, "categories") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")

    def * = (id, title) <> ((Category.apply _).tupled, Category.unapply)
  }

  protected val categories = TableQuery[Categories]
}
