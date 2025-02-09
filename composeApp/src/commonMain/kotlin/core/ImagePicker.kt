package core

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import dev.icerock.moko.media.compose.BindMediaPickerEffect
import dev.icerock.moko.media.compose.rememberMediaPickerControllerFactory
import dev.icerock.moko.media.compose.toImageBitmap
import dev.icerock.moko.media.picker.CanceledException
import dev.icerock.moko.media.picker.MediaSource
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImagePickerButton(
    modifier: Modifier = Modifier,
    onImageResult: (ImageBitmap) -> Unit,
    onCancel: () -> Unit,
    onError: (Throwable) -> Unit,
    tintColor: Color = Color.White,
    resource: DrawableResource
) {
    val factory = rememberMediaPickerControllerFactory()
    val picker = remember(factory) { factory.createMediaPickerController() }
    val coroutineScope = rememberCoroutineScope()
    BindMediaPickerEffect(picker)
    Image(
        modifier = modifier.clickable {
            coroutineScope.launch {
                try {
                    val result = picker.pickImage(MediaSource.GALLERY)
                    onImageResult(result.toImageBitmap())
                } catch (_: CanceledException) {
                    onCancel()
                } catch (error: Throwable) {
                    // denied permission or file read error
                    onError(error)
                }
            }
        },
        painter = painterResource(resource),
        colorFilter = ColorFilter.tint(color = tintColor),
        contentDescription = null
    )
}

