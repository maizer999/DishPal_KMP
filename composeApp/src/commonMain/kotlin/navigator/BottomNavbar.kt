package navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import designsystem.Gray100
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.add
import dishpal.composeapp.generated.resources.chat_selected
import dishpal.composeapp.generated.resources.chats
import dishpal.composeapp.generated.resources.home
import dishpal.composeapp.generated.resources.home_selected
import dishpal.composeapp.generated.resources.post
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ColumnScope.bottomNavbar(modifier: Modifier = Modifier,
                 currentScreen: Screen,
                 onHomeClicked: () -> Unit,
                 onPostClicked: () -> Unit,
                 onChatClicked: () -> Unit) {

    Row(
        modifier = modifier
            .height(55.dp)
            .background(color = Color.White)
            .drawBehind {
                val strokeWidth = 2.dp
                drawLine(
                    color = Color.LightGray,
                    Offset(0f, 0f),
                    Offset(size.width, 0f),
                    strokeWidth.value
                )
            }
            .padding(start = 30.dp, end = 30.dp, top = 0.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavbarItem(
            drawable = Res.drawable.home,
            selectedDrawable = Res.drawable.home_selected,
            text = Res.string.home,
            isSelected = currentScreen == Screen.Home,
            tint = ColorFilter.tint(color = Gray100),
            onClick = onHomeClicked
        )
        NavbarItem(
            drawable = Res.drawable.add,
            selectedDrawable = Res.drawable.add,
            height = 33.dp,
            textMargin = 0.dp,
            text = Res.string.post,
            isSelected = currentScreen == Screen.NewPost,
            onClick = onPostClicked
        )
        NavbarItem(
            drawable = Res.drawable.chats,
            selectedDrawable = Res.drawable.chat_selected,
            text = Res.string.chats,
            isSelected = currentScreen == Screen.Chats,
            tint = ColorFilter.tint(color = Gray100),
            onClick = onChatClicked
        )
    }
}