package db.schema

import kotlin.Double
import kotlin.Long
import kotlin.String

public data class User(
  public val id: Long,
  public val rate: Double?,
  public val rateCount: Long?,
  public val userInitials: String,
  public val userAvatarUrl: String?,
  public val fullName: String,
)
