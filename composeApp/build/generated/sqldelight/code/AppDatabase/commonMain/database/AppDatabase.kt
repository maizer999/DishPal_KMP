package database

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import database.composeApp.newInstance
import database.composeApp.schema
import db.schema.PostQueries
import db.schema.UserQueries
import kotlin.Unit

public interface AppDatabase : Transacter {
  public val postQueries: PostQueries

  public val userQueries: UserQueries

  public companion object {
    public val Schema: SqlSchema<QueryResult.Value<Unit>>
      get() = AppDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): AppDatabase =
        AppDatabase::class.newInstance(driver)
  }
}
