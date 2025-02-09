package feature_onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.onboard3
import dishpal.composeapp.generated.resources.onboard3_subtitle
import dishpal.composeapp.generated.resources.onboard3_title
import navigator.NavigatorViewModel
import navigator.Screen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Onboard3(
    modifier: Modifier,
    navigatorViewModel: NavigatorViewModel
) {
    Column(modifier = modifier.background(color = Color.White)) {
        ImageHeader(img = Res.drawable.onboard3, title = Res.string.onboard3_title)
        Text(
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 18.sp
            ),
            text = stringResource(Res.string.onboard3_subtitle)
        )
        NextButton {
            navigatorViewModel.navigate(Screen.Home)
        }
    }
}