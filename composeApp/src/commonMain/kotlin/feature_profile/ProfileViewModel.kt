package feature_profile

import domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repositories.UserRepository

data class ProfileState(
    val user: User? = null,
    val isLoading: Boolean = true,
    val error: Throwable? = null
)

class ProfileViewModel(
    private val scope: CoroutineScope,
    private val userRepository: UserRepository
) {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun getUser() {
        scope.launch {
            val user = userRepository.getUser()
            user?.let {
                _state.update { it.copy(user = user) }
            }
        }
    }

    fun saveUserName(name: String) {
        scope.launch {
            userRepository.saveUser(user = User(
                id = 0,
                rate = 0f,
                rateCount = null,
                userAvatarUrl = null,
                userInitials = name.substring(0, 2),
                fullName = name
            ))
        }
    }

}