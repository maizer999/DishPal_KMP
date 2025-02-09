package navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import feature_onboard.Onboard1
import feature_onboard.Onboard2
import feature_onboard.Onboard3
import feature_post.NewPostScreen

@Composable
fun Navigator(modifier: Modifier,
              navigatorVM: NavigatorViewModel) {
    when (navigatorVM.currentScreen.collectAsState().value) {
        is Screen.Onboard1 -> Onboard1(modifier = modifier, navigatorVM)
        is Screen.Onboard2 -> Onboard2(modifier = modifier, navigatorVM)
        is Screen.Onboard3 -> Onboard3(modifier = modifier, navigatorVM)
        is Screen.NewPost -> NewPostScreen(modifier = modifier, navigatorVM = navigatorVM)

        else -> {
            MainScreen(modifier = modifier,
                navigatorVM = navigatorVM)
        }
    }
}