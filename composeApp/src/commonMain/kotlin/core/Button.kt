package core

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.media.compose.BindMediaPickerEffect
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.camera
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DefaultButton(modifier: Modifier = Modifier,
                  onClick: () -> Unit,
                  isLoading: Boolean = false,
                  text: String) {
    Button(
        modifier = modifier,
        onClick = {
           if (!isLoading) onClick()
        },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 3.dp
            )
        } else {
            Text(
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp)
                    .padding(10.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White
                ),
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageButton(modifier: Modifier = Modifier,
                 onClick: () -> Unit,
                 tintColor: Color = Color.White,
                 resource: DrawableResource) {
        Image(
            modifier = modifier.clickable {
                onClick()
            },
            painter = painterResource(resource),
            colorFilter = ColorFilter.tint(color = tintColor),
            contentDescription = null
        )
}

