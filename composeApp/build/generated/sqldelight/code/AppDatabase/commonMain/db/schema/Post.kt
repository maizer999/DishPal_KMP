package db.schema

import kotlin.Long
import kotlin.String

public data class Post(
  public val id: Long?,
  public val localId: Long,
  public val createdByUserId: Long,
  public val dishImageUrl: String?,
  public val dishName: String,
  public val dishType: String?,
  public val dishDescription: String,
  public val recipe: String,
  public val isLiked: Long,
  public val isDishPal: Long,
)
