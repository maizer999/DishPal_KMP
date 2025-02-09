package domain

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val rate: Float?,
    val rateCount: Long?,
    val userInitials: String,
    val userAvatarUrl: String?,
    val fullName: String
)
