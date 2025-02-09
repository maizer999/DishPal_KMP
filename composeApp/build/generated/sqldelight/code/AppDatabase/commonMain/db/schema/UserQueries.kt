package db.schema

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Double
import kotlin.Long
import kotlin.String

public class UserQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> selectUserById(id: Long, mapper: (
    id: Long,
    rate: Double?,
    rateCount: Long?,
    userInitials: String,
    userAvatarUrl: String?,
    fullName: String,
  ) -> T): Query<T> = SelectUserByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getDouble(1),
      cursor.getLong(2),
      cursor.getString(3)!!,
      cursor.getString(4),
      cursor.getString(5)!!
    )
  }

  public fun selectUserById(id: Long): Query<User> = selectUserById(id) { id_, rate, rateCount,
      userInitials, userAvatarUrl, fullName ->
    User(
      id_,
      rate,
      rateCount,
      userInitials,
      userAvatarUrl,
      fullName
    )
  }

  public fun <T : Any> selectAllUsers(mapper: (
    id: Long,
    rate: Double?,
    rateCount: Long?,
    userInitials: String,
    userAvatarUrl: String?,
    fullName: String,
  ) -> T): Query<T> = Query(94_423_013, arrayOf("User"), driver, "User.sq", "selectAllUsers",
      "SELECT User.id, User.rate, User.rateCount, User.userInitials, User.userAvatarUrl, User.fullName FROM User") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getDouble(1),
      cursor.getLong(2),
      cursor.getString(3)!!,
      cursor.getString(4),
      cursor.getString(5)!!
    )
  }

  public fun selectAllUsers(): Query<User> = selectAllUsers { id, rate, rateCount, userInitials,
      userAvatarUrl, fullName ->
    User(
      id,
      rate,
      rateCount,
      userInitials,
      userAvatarUrl,
      fullName
    )
  }

  public fun insertUser(
    id: Long?,
    rate: Double?,
    rateCount: Long?,
    userInitials: String,
    userAvatarUrl: String?,
    fullName: String,
  ) {
    driver.execute(-1_215_870_714, """
        |INSERT INTO User (
        |    id,
        |    rate,
        |    rateCount,
        |    userInitials,
        |    userAvatarUrl,
        |    fullName
        |) VALUES (?, ?, ?, ?, ?, ?)
        """.trimMargin(), 6) {
          bindLong(0, id)
          bindDouble(1, rate)
          bindLong(2, rateCount)
          bindString(3, userInitials)
          bindString(4, userAvatarUrl)
          bindString(5, fullName)
        }
    notifyQueries(-1_215_870_714) { emit ->
      emit("User")
    }
  }

  public fun updateUser(
    rate: Double?,
    rateCount: Long?,
    userInitials: String,
    userAvatarUrl: String?,
    fullName: String,
    id: Long,
  ) {
    driver.execute(1_816_999_702, """
        |UPDATE User
        |SET
        |    rate = ?,
        |    rateCount = ?,
        |    userInitials = ?,
        |    userAvatarUrl = ?,
        |    fullName = ?
        |WHERE id = ?
        """.trimMargin(), 6) {
          bindDouble(0, rate)
          bindLong(1, rateCount)
          bindString(2, userInitials)
          bindString(3, userAvatarUrl)
          bindString(4, fullName)
          bindLong(5, id)
        }
    notifyQueries(1_816_999_702) { emit ->
      emit("User")
    }
  }

  public fun removeAllUsers() {
    driver.execute(1_842_643_821, """DELETE FROM User""", 0)
    notifyQueries(1_842_643_821) { emit ->
      emit("User")
    }
  }

  private inner class SelectUserByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("User", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("User", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_961_581_253,
        """SELECT User.id, User.rate, User.rateCount, User.userInitials, User.userAvatarUrl, User.fullName FROM User WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "User.sq:selectUserById"
  }
}
