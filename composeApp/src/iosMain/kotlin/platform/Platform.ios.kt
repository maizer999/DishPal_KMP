package platform

import domain.LocationInfo
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.datetime.Clock
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.darwin.NSObject

class IOSPlatform: Platform {
    override val name: String = "iOS"
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun openLink(address: String) {
    NSURL.URLWithString(address)?.let {
        UIApplication.sharedApplication().openURL(it)
    }
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class LocationClient: NSObject(), CLLocationManagerDelegateProtocol {
    private val manager = CLLocationManager()
    private lateinit var callBack: (LocationInfo) -> Unit

    actual fun requestLocation(onResult: (LocationInfo) -> Unit) {
        manager.delegate = this
        callBack = onResult
        manager.requestLocation()
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
        didUpdateLocations.firstOrNull()?.let {
            val location = it as CLLocation
            location.coordinate.useContents {
                callBack(
                    LocationInfo(
                        latitude = latitude,
                        longitude = longitude,
                        timeStamp = Clock.System.now().toEpochMilliseconds(),
                        accuracy = 0.0
                    )
                )
            }
        }
    }

    override fun locationManagerDidChangeAuthorization(manager: CLLocationManager) {
        when (manager.authorizationStatus) {
            1, 2 -> {

            }
            3, 4 -> {

            }
        }
    }

    override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
        //add code
    }

}
