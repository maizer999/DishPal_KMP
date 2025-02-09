package feature_post

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate


data class NewPostUiState(
    val imageUrls: List<String>? = null,
    val myPalsOnly: Boolean = true,
    val name: String? = null,
    val images: List<ImageBitmap> = listOf(),
    val desc: String? = null,
    val isLoading: Boolean = false,
    val availableDates: List<LocalDate> = listOf(),
    val error: Throwable? = null,
    val currentImageIndex: Int = 0
)

class NewPostViewModel {
    private val _uiState = MutableStateFlow(NewPostUiState())
    val uiState = _uiState.asStateFlow()

    fun submit() {

    }

    fun userEntry(newState: NewPostUiState) {
        _uiState.update { newState }
    }

    fun onImageAdd(img: ImageBitmap) {
        _uiState.value.images.toMutableList().apply {
            add(img)
            _uiState.update { it.copy(images = this) }
        }
    }

    fun removeImage() {
        _uiState.update { currentState ->
            val images = currentState.images.toMutableList().apply {
                val index = currentState.currentImageIndex
                if (index < size) removeAt(index)
            }
            currentState.copy(images = images)
        }
    }

}