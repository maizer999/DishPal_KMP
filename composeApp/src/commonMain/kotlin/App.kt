import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import designsystem.DarkTheme
import designsystem.LightTheme
import di.appModule
import kotlinx.coroutines.flow.StateFlow
import navigator.Navigator
import navigator.NavigatorViewModel
import navigator.PlatformEvent
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
@Preview
fun App(event: StateFlow<PlatformEvent>) {
    KoinApplication(application = {
        modules(appModule)
    }) {
        val navigatorVM : NavigatorViewModel = koinInject()

        event.collectAsState().value.let {
            navigatorVM.watch(event.value)
            println("PlatformEvent (DeepLink, ...) ")
        }

        MaterialTheme(colors = if (isSystemInDarkTheme()) DarkTheme() else LightTheme()) {
            Navigator(modifier = Modifier.fillMaxSize(),
                navigatorVM = navigatorVM)
        }
    }
}
