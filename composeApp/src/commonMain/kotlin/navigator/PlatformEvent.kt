package navigator

import platform.EventType
import kotlin.random.Random

data class PlatformEvent(
    val message: String = "",
    val error: Exception? = null,
    val eType: EventType = EventType.EMPTY,
    val timeout: Boolean = false,
    val id: Long = Random.nextLong(),
)