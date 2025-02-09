import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import navigator.PlatformEvent
import platform.EventType

fun onEvent(eventType: EventType, params: String) {
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    coroutineScope.launch {
            event.emit(PlatformEvent(
                eType = eventType,
                message = params
            ))
    }
}

val event: MutableStateFlow<PlatformEvent> = MutableStateFlow(PlatformEvent())

fun MainViewController() = ComposeUIViewController { App(event) }