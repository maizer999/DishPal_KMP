package feature_home

import platform.LocationClient
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import domain.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repositories.PostRepository

data class HomeState(
    val posts: MutableList<Post> = mutableListOf(),
    val isLoading: Boolean = true,
    val error: Throwable? = null
)

class HomeViewModel(
    private val scope: CoroutineScope,
    private val postRepository: PostRepository,
    private val locationClient: LocationClient
) {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        scope.launch {
            getAllPosts()
        }
    }

    fun getAllPosts() {
        scope.launch {
            try {
                val result = _state.value.posts + postRepository.getAll()
                print("maizer ==> ${result}")
                _state.update {
                    it.copy(posts = result.toMutableList(), isLoading = false)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = e)
                }
            }
        }
    }

    fun getLocation(permissionController: PermissionsController) {
        scope.launch {
            checkLocationPermission(permissionController)
            try {
                permissionController.providePermission(Permission.LOCATION)
                permissionController.providePermission(Permission.COARSE_LOCATION)
                locationClient.requestLocation {
                    println(" LocationF: ${it.latitude} ${it.longitude}")
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = Exception("Error in getting the location."))
                }
            }
        }
    }

    fun openLink() {
        platform.openLink(
            "https://samples.auth0.com/authorize?\n" +
                    "client_id=kbyuFDidLLm280LIwVFiazOqjO3ty8KH\n" +
                    "&redirect_uri= dishpal://callback\n" +
                    "&scope=openid profile email phone address\n" +
                    "&response_type=code\n" +
                    "&state=f778392c057d8195241de1fc8d23dadc8ee82c0f"
        )
    }

    private suspend fun checkLocationPermission(permissionController: PermissionsController) {
        if (!permissionController.isPermissionGranted(Permission.LOCATION)) {
            _state.update {
                it.copy(error = Exception("Location Permission is not granted."))
            }
        }
        if (!permissionController.isPermissionGranted(Permission.COARSE_LOCATION)) {
            _state.update {
                it.copy(error = Exception("COARSE LOCATION Permission is not granted."))
            }
        }
    }


}