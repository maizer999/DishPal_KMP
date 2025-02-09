package domain

import kotlinx.serialization.Serializable

@Serializable
data class Post(
  val createdByUserId: Long = 1,
  val createdBy: User? = null,
  val dishImageUrl: List<String>?,
  val dishName: String,
  val dishType: String?,
  val dishDescription: String,
  val recipe: String = "",
  val isLiked: Boolean = false,
  val isDishPal: Boolean = false,
  val rate: Float = 3.0f,
  val rateCount: Int = 0,
  val id: Long,
)
