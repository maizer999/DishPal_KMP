package core

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import designsystem.White300
import io.kamel.core.Resource
import io.kamel.image.asyncPainterResource

@Composable
fun ImageBanner(modifier: Modifier = Modifier,
                imageUrl: String? = null,
                imageBitmap: ImageBitmap? = null,
                drawShadow: Boolean = true,
                shadowColor: Color = Color.LightGray
) {
    if (imageUrl != null) {
        when (val resource = asyncPainterResource(imageUrl)) {
            is Resource.Success -> {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .drawBehind {
                                if (drawShadow) drawRect(
                                    Brush.verticalGradient(colorStops = arrayOf(
                                        0.7f to Color.Transparent,
                                        1f to shadowColor,)
                                    )
                                )
                            }
                    )
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.FillWidth,
                        painter = resource.value,
                        contentDescription = "image"
                    )
                }
            }
            else -> {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .drawBehind {
                            if (drawShadow) drawRect(
                                Brush.verticalGradient(colorStops = arrayOf(
                                    0.7f to Color.Transparent,
                                    1f to shadowColor,)
                                )
                            )
                        }
                )
            }
        }
    } else {
        Box(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawRect(
                            Brush.verticalGradient(colorStops = arrayOf(
                                0.7f to Color.Transparent,
                                1f to shadowColor,)
                            )
                        )
                    }
            )
            imageBitmap?.let {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    bitmap = it,
                    contentDescription = "image"
                )
            }
        }
    }

}