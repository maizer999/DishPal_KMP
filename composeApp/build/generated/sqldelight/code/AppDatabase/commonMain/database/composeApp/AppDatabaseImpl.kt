package database.composeApp

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import database.AppDatabase
import db.schema.PostQueries
import db.schema.UserQueries
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlSchema<QueryResult.Value<Unit>>
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), AppDatabase {
  override val postQueries: PostQueries = PostQueries(driver)

  override val userQueries: UserQueries = UserQueries(driver)

  public object Schema : SqlSchema<QueryResult.Value<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.Value<Unit> {
      driver.execute(null, """
          |CREATE TABLE Post (
          |    id INTEGER,
          |    localId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
          |    createdByUserId INTEGER NOT NULL,
          |    dishImageUrl TEXT,
          |    dishName TEXT NOT NULL,
          |    dishType TEXT,
          |    dishDescription TEXT NOT NULL,
          |    recipe TEXT NOT NULL,
          |    isLiked INTEGER NOT NULL DEFAULT 0,
          |    isDishPal INTEGER NOT NULL DEFAULT 0
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE User (
          |    id INTEGER NOT NULL PRIMARY KEY,
          |    rate REAL, -- Corresponds to Float in Kotlin and can be NULL
          |    rateCount INTEGER, -- Corresponds to Int in Kotlin and can be NULL
          |    userInitials TEXT NOT NULL,
          |    userAvatarUrl TEXT, -- Can be NULL
          |    fullName TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      return QueryResult.Unit
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.Value<Unit> = QueryResult.Unit
  }
}
