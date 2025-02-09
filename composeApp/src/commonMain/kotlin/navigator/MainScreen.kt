package navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feature_chat.ChatScreen
import core.asImage
import designsystem.White300
import feature_home.HomeScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import feature_post.PostScreen
import feature_profile.ProfileScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier,
              navigatorVM: NavigatorViewModel
) {
    val currentScreen =  navigatorVM.currentScreen.collectAsState().value
    val userState = navigatorVM.state.collectAsState().value

    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = White300)
    ) {
        headerBar(
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            user = userState.user,
            onProfileClicked = {
                navigatorVM.navigate(Screen.Profile)
            }
        )

        ScreenContainer(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .heightIn(min = 300.dp),
            navigator = navigatorVM,
            currentScreen = currentScreen
        )

        bottomNavbar(
            modifier = Modifier.fillMaxWidth()
                .height(55.dp),
            currentScreen = currentScreen,
            onHomeClicked = {
                navigatorVM.navigate(Screen.Home)
            },
            onPostClicked = {
                navigatorVM.navigate(Screen.NewPost)
            },
            onChatClicked = {
                navigatorVM.navigate(Screen.Chats)
            }
        )
    }
}

@Composable
fun ColumnScope.ScreenContainer(
    modifier: Modifier = Modifier,
    navigator: NavigatorViewModel,
    currentScreen: Screen) {
    when (currentScreen) {
        is Screen.Home -> HomeScreen(modifier = modifier, onNavigate = navigator::navigate)
        is Screen.Chats -> ChatScreen(modifier = modifier)
        is Screen.Post -> PostScreen(modifier = modifier, id = currentScreen.postId)
        is Screen.Profile -> ProfileScreen(modifier = modifier, navigatorViewModel = navigator)
        else -> {
            // It's handled in Navigator
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NavbarItem(modifier: Modifier = Modifier,
               drawable: DrawableResource,
               selectedDrawable: DrawableResource,
               height: Dp = 25.dp,
               textMargin: Dp = 3.dp,
               textSize: TextUnit = 13.sp,
               text: StringResource,
               onClick: () -> Unit,
               tint: ColorFilter? = null,
               isSelected: Boolean = false) {

    Column(modifier = modifier) {
        if (!isSelected) {
            drawable.asImage(
                modifier = Modifier
                    .height(height)
                    .width(height)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        onClick()
                    },
                tint = tint
            )
        } else {
            selectedDrawable.asImage(
                modifier = Modifier
                    .height(height)
                    .width(height)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        onClick()
                    }
            )
        }
        Text(
            modifier = Modifier
                .padding(top = textMargin)
                .align(Alignment.CenterHorizontally),
            text = stringResource(text),
            style = TextStyle(fontSize = textSize)
        )
    }
}