package platform

import domain.LocationInfo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun openLink(address: String)

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class LocationClient() {
    fun requestLocation(onResult: (LocationInfo) -> Unit)
}

enum class EventType(name: String) {
    EMPTY("Empty"),
    BACK_PRESS("Back"),
    DEEP_LINK("DeepLink"),
    LOCATION("Location")
}

