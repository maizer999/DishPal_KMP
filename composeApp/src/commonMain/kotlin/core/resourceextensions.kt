package core

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun DrawableResource.asImage(modifier: Modifier = Modifier,
                             contentScale: ContentScale = ContentScale.Fit,
                                tint: ColorFilter? = null
) {
    Image(
        modifier = modifier,
        contentScale = contentScale,
        painter = painterResource(this),
        contentDescription = null,
        colorFilter = tint
    )
}