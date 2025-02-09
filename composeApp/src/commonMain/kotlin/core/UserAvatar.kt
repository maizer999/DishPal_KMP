package core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.core.utils.URI
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@Composable
fun UserAvatar(modifier: Modifier = Modifier,
               size: Dp = 40.dp,
               border: Dp = 1.dp,
               avatarUrl: String?, initials: String) {
    if (avatarUrl != null) {
        KamelImage(
            resource = asyncPainterResource(URI(avatarUrl)),
            modifier = modifier
                .clip(CircleShape)
                .width(size).height(size)
                .border(border, Color.White, CircleShape)
                .background(color = MaterialTheme.colors.primary),
            contentDescription = null
        )
    } else {
        Box(modifier = modifier
            .clip(CircleShape)
            .width(size).height(size)
            .border(border, Color.White, CircleShape)
            .background(color = MaterialTheme.colors.primary)) {
            Text(text = initials,
                style = TextStyle(fontSize = 13.sp),
                modifier = Modifier.align(Alignment.Center))
        }
    }
}
