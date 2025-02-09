package navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import core.DefaultButton
import core.DefaultTextField
import core.asImage
import designsystem.White300
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.name
import dishpal.composeapp.generated.resources.next
import dishpal.composeapp.generated.resources.profile
import domain.User
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun headerBar(modifier: Modifier,
              user: User?,
              onProfileClicked: () -> Unit) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colors.primary),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Res.drawable.profile.asImage(
            modifier = Modifier
                .height(30.dp)
                .padding(start = 10.dp)
                .clickable {
                    onProfileClicked()
                },
            tint = ColorFilter.tint(color = White300),
        )

        user?.let {
            Text(
                modifier = Modifier.padding(3.dp),
                text = it.fullName,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White
                )
            )
        }

        // TODO: Search
    }
}