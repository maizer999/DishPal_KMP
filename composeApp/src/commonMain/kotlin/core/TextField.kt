package core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import designsystem.Gray100
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DefaultTextField(
    modifier: Modifier,
    text: String,
    placeHolder: StringResource,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { onValueChanged(it)},
        placeholder = { Text(text = stringResource(placeHolder)) },
        maxLines = 1,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = MaterialTheme.colors.primary,
            disabledBorderColor = Gray100,
            placeholderColor = Gray100
        )
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DefaultMultilineTextField(
    modifier: Modifier,
    text: String,
    placeHolder: StringResource,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { onValueChanged(it)},
        placeholder = { Text(text = stringResource(placeHolder)) },
        maxLines = 10,
        singleLine = false,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = MaterialTheme.colors.primary,
            disabledBorderColor = Gray100,
            placeholderColor = Gray100
        )
    )
}