package domain

import kotlinx.serialization.Serializable

@Serializable
data class LocationInfo(
    val latitude: Double,
    val longitude: Double,
    val timeStamp: Long,
    val accuracy: Double
)
