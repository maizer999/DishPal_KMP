package navigator

import domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import platform.EventType
import repositories.UserRepository


data class NavigatorState(
    val user: User? = null,
    val isLoading: Boolean = true,
    val error: Throwable? = null
)

class NavigatorViewModel(
    private val scope: CoroutineScope,
    private val userRepository: UserRepository
){
    private val backStack: MutableList<Screen> = mutableListOf()
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Onboard1)
    val currentScreen = _currentScreen.asStateFlow()


    private val _state = MutableStateFlow(NavigatorState())
    val state: StateFlow<NavigatorState> = _state.asStateFlow()

    init {
        loadUser()
    }

    fun loadUser() {
        scope.launch {
            userRepository.getUser()?.let {
                _state.update { s ->
                    s.copy(user = it)
                }
            }
        }
    }

    fun navigate(screen: Screen, ignoreBackStack: Boolean = false) {
        _currentScreen.update { screen }
        if (screen.addToBackStack && !ignoreBackStack) backStack.add(screen)
    }

    fun watch(event: PlatformEvent) {
        onEvent(event)
    }

    fun back() {
        backStack.removeLastOrNull()
        if (backStack.isNotEmpty()) {
            navigate(backStack.last(), ignoreBackStack = true)
        } else if (_currentScreen.value != Screen.Home) {
            navigate(Screen.Home, ignoreBackStack = true)
        }
    }

    private fun onDeepLink(address: String) {
        if (address.isNotEmpty()) {
            val screenName = address.split("/").last()
            when {
                screenName.startsWith("home") -> navigate(Screen.Home)
                screenName.startsWith("chat") -> navigate(Screen.Chats)
                screenName.startsWith("post") -> navigate(Screen.NewPost)
                else -> {
                    navigate(Screen.Home)
                }
            }
        }
    }

    private fun onEvent(event: PlatformEvent) {
        when (event.eType) {
            EventType.BACK_PRESS -> back()
            EventType.DEEP_LINK -> onDeepLink(event.message)
            // This is one way of handling the Platform-Specific events
            // like (Location Updates, Notifications, etc.)
            // It is mostly useful for events that are going to affect the
            // Screen and UI.
            else -> { }
        }
    }
}
