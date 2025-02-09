package db.schema

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class PostQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> selectAllPosts(mapper: (
    id: Long?,
    localId: Long,
    createdByUserId: Long,
    dishImageUrl: String?,
    dishName: String,
    dishType: String?,
    dishDescription: String,
    recipe: String,
    isLiked: Long,
    isDishPal: Long,
  ) -> T): Query<T> = Query(2_022_094_661, arrayOf("Post"), driver, "Post.sq", "selectAllPosts",
      "SELECT Post.id, Post.localId, Post.createdByUserId, Post.dishImageUrl, Post.dishName, Post.dishType, Post.dishDescription, Post.recipe, Post.isLiked, Post.isDishPal FROM Post") {
      cursor ->
    mapper(
      cursor.getLong(0),
      cursor.getLong(1)!!,
      cursor.getLong(2)!!,
      cursor.getString(3),
      cursor.getString(4)!!,
      cursor.getString(5),
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getLong(8)!!,
      cursor.getLong(9)!!
    )
  }

  public fun selectAllPosts(): Query<Post> = selectAllPosts { id, localId, createdByUserId,
      dishImageUrl, dishName, dishType, dishDescription, recipe, isLiked, isDishPal ->
    Post(
      id,
      localId,
      createdByUserId,
      dishImageUrl,
      dishName,
      dishType,
      dishDescription,
      recipe,
      isLiked,
      isDishPal
    )
  }

  public fun <T : Any> selectPost(id: Long?, mapper: (
    id: Long?,
    localId: Long,
    createdByUserId: Long,
    dishImageUrl: String?,
    dishName: String,
    dishType: String?,
    dishDescription: String,
    recipe: String,
    isLiked: Long,
    isDishPal: Long,
  ) -> T): Query<T> = SelectPostQuery(id) { cursor ->
    mapper(
      cursor.getLong(0),
      cursor.getLong(1)!!,
      cursor.getLong(2)!!,
      cursor.getString(3),
      cursor.getString(4)!!,
      cursor.getString(5),
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getLong(8)!!,
      cursor.getLong(9)!!
    )
  }

  public fun selectPost(id: Long?): Query<Post> = selectPost(id) { id_, localId, createdByUserId,
      dishImageUrl, dishName, dishType, dishDescription, recipe, isLiked, isDishPal ->
    Post(
      id_,
      localId,
      createdByUserId,
      dishImageUrl,
      dishName,
      dishType,
      dishDescription,
      recipe,
      isLiked,
      isDishPal
    )
  }

  public fun insertPost(
    id: Long?,
    localId: Long?,
    createdByUserId: Long,
    dishImageUrl: String?,
    dishName: String,
    dishType: String?,
    dishDescription: String,
    recipe: String,
    isLiked: Long,
    isDishPal: Long,
  ) {
    driver.execute(-1_481_307_664, """
        |INSERT INTO Post (
        |    id,
        |    localId,
        |    createdByUserId,
        |    dishImageUrl,
        |    dishName,
        |    dishType,
        |    dishDescription,
        |    recipe,
        |    isLiked,
        |    isDishPal
        |) VALUES (?, ?,  ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 10) {
          bindLong(0, id)
          bindLong(1, localId)
          bindLong(2, createdByUserId)
          bindString(3, dishImageUrl)
          bindString(4, dishName)
          bindString(5, dishType)
          bindString(6, dishDescription)
          bindString(7, recipe)
          bindLong(8, isLiked)
          bindLong(9, isDishPal)
        }
    notifyQueries(-1_481_307_664) { emit ->
      emit("Post")
    }
  }

  public fun updatePost(
    createdByUserId: Long,
    dishImageUrl: String?,
    dishName: String,
    dishType: String?,
    dishDescription: String,
    recipe: String,
    isLiked: Long,
    isDishPal: Long,
    id: Long?,
  ) {
    driver.execute(null, """
        |UPDATE Post
        |SET
        |    createdByUserId = ?,
        |    dishImageUrl = ?,
        |    dishName = ?,
        |    dishType = ?,
        |    dishDescription = ?,
        |    recipe = ?,
        |    isLiked = ?,
        |    isDishPal = ?
        |WHERE id ${ if (id == null) "IS" else "=" } ?
        """.trimMargin(), 9) {
          bindLong(0, createdByUserId)
          bindString(1, dishImageUrl)
          bindString(2, dishName)
          bindString(3, dishType)
          bindString(4, dishDescription)
          bindString(5, recipe)
          bindLong(6, isLiked)
          bindLong(7, isDishPal)
          bindLong(8, id)
        }
    notifyQueries(1_551_562_752) { emit ->
      emit("Post")
    }
  }

  public fun removePost(id: Long?) {
    driver.execute(null, """DELETE FROM Post WHERE id ${ if (id == null) "IS" else "=" } ?""", 1) {
          bindLong(0, id)
        }
    notifyQueries(-1_348_902_341) { emit ->
      emit("Post")
    }
  }

  public fun removeAllPosts() {
    driver.execute(-524_651_827, """DELETE FROM Post""", 0)
    notifyQueries(-524_651_827) { emit ->
      emit("Post")
    }
  }

  private inner class SelectPostQuery<out T : Any>(
    public val id: Long?,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Post", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Post", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(null,
        """SELECT Post.id, Post.localId, Post.createdByUserId, Post.dishImageUrl, Post.dishName, Post.dishType, Post.dishDescription, Post.recipe, Post.isLiked, Post.isDishPal FROM Post WHERE id ${ if (id == null) "IS" else "=" } ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "Post.sq:selectPost"
  }
}
