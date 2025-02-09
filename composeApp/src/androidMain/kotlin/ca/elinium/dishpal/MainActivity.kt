package ca.elinium.dishpal

import App
import platform.EventType
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import di.initializeAppContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import navigator.PlatformEvent

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeAppContext(applicationContext)

        setContent {
            App(event)
            BackHandler {
                onEvent(EventType.BACK_PRESS, "")
            }
        }

        val data: Uri? = intent?.data
        data?.let {
            onEvent(EventType.DEEP_LINK, it.toString())
        }
    }

    private fun onEvent(eventType: EventType, params: String) {
        lifecycleScope.launch {
            launch {
                event.emit(
                    PlatformEvent(
                        eType = eventType,
                        message = params
                    )
                )
            }
        }
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App(MutableStateFlow(PlatformEvent()))
}

val event: MutableStateFlow<PlatformEvent> = MutableStateFlow(PlatformEvent())
