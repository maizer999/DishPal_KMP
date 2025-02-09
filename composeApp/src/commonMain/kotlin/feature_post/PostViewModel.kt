package feature_post

import domain.Post
import domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repositories.PostRepository

data class UiState(
    val post: Post? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)

class PostViewModel(
    private val scope: CoroutineScope,
    private val postRepository: PostRepository
) {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun refresh(id: Long) {
        scope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            val post = postRepository.get(id)
            _state.update { UiState(post = post) }
        }
    }
}