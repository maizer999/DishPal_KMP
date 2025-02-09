package feature_onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.DefaultButton
import core.asImage
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.next
import dishpal.composeapp.generated.resources.onboard1
import dishpal.composeapp.generated.resources.onboard1_subtitle
import dishpal.composeapp.generated.resources.onboard1_title
import navigator.NavigatorViewModel
import navigator.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Onboard1(
    modifier: Modifier,
    navigatorViewModel: NavigatorViewModel
) {
    Column(modifier = modifier.background(color = Color.White)) {
        ImageHeader(img = Res.drawable.onboard1, title = Res.string.onboard1_title)
        Text(
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 18.sp
            ),
            text = stringResource(Res.string.onboard1_subtitle)
        )
        NextButton {
            navigatorViewModel.navigate(Screen.Onboard2)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ColumnScope.ImageHeader(modifier: Modifier = Modifier,
                            img: DrawableResource,
                            title: StringResource) {
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(top = 50.dp)
        .weight(1f)) {
        img.asImage(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            contentScale = ContentScale.Fit)
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            Text(modifier = Modifier
                .padding(top = 60.dp, end = 10.dp)
                .width(150.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                text = stringResource(title)
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ColumnScope.NextButton(modifier: Modifier = Modifier,
                           onClick: () -> Unit) {
    DefaultButton(
        modifier = modifier
            .width(200.dp)
            .padding(
                top = 30.dp,
                bottom = 30.dp
            )
            .align(Alignment.CenterHorizontally),
        onClick = {
            onClick()
        },
        text = stringResource(Res.string.next)
    )
}