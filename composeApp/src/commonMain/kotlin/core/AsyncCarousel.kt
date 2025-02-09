package core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import designsystem.White300

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AsyncCarousel(
    modifier: Modifier = Modifier,
    urls: List<String> = listOf(),
    images: List<ImageBitmap> = listOf(),
    shadowColor: Color = Color.DarkGray,
    onPageLoad: (Int) -> Unit
) {
    val max = if (urls.isNotEmpty()) urls.size else images.size
    val pagerState: PagerState = rememberPagerState(pageCount = { max })
    LaunchedEffect(pagerState.currentPage) {
        onPageLoad(pagerState.currentPage)
    }
    Box(modifier = modifier.drawBehind {
        drawRect(
            Brush.verticalGradient(
                colorStops = arrayOf(
                    0f to Color.Transparent,
                    1f to shadowColor,)
            )
        )
    }) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize(),
            state = pagerState
        ) { page ->
            if (images.isNotEmpty()) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = images[page],
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            } else {
                ImageBanner(
                    modifier = Modifier.fillMaxSize(),
                    imageUrl = urls[page],
                    drawShadow = false
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 0..< pagerState.pageCount) {
                    Box(
                        modifier = Modifier
                            .size(width = 20.dp, height =20.dp)
                            .padding(5.dp)
                            .clip(CircleShape)
                            .background(
                                color = if (i == pagerState.currentPage) MaterialTheme.colors.primary else Color.LightGray
                            )
                    )
                }
            }
        }
    }
}