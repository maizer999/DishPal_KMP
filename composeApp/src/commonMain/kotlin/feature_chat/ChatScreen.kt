package feature_chat

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text


@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Chats")
}