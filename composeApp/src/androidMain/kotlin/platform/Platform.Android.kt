package platform

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import di.appContext
import domain.LocationInfo
import java.util.Date

class AndroidPlatform : Platform {
    override val name: String = "Android" // "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun openLink(address: String) {
    val browserIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(address)
    )
    browserIntent.addFlags(FLAG_ACTIVITY_NEW_TASK)
    appContext.startActivity(browserIntent)
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class LocationClient {
    private val locationClient = LocationServices.getFusedLocationProviderClient(appContext)

    @SuppressLint("MissingPermission")
    actual fun requestLocation(onResult: (LocationInfo) -> Unit) {
        locationClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY, CancellationTokenSource().token,
        ).addOnSuccessListener { location ->
            location?.let {
                val info =LocationInfo(latitude = location.latitude,
                    longitude = location.longitude,
                    timeStamp = Date().time,
                    accuracy = location.accuracy.toDouble()
                )

                onResult(info)
//                MainScope().launch {
//                    event.emit(
//                        PlatformEvent(
//                            eType = EventType.LOCATION,
//                            message = Json.encodeToString(info)
//                        )
//                    )
//                }
                }
            }
    }
}

