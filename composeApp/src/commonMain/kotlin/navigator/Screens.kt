package navigator

sealed class Screen(val id: Int,
                    val safeArgs: Map<String, String> = mapOf(),
                    val addToBackStack: Boolean = false) {
    data object Home: Screen(id = 0)
    data object Chats: Screen(id = 1)
    data object Profile: Screen(id = 2)
    data object NewPost: Screen(id = 2, addToBackStack = true)
    data object Onboard1: Screen(id = 3)
    data object Onboard2: Screen(id = 4)
    data object Onboard3: Screen(id = 5)
    data class Post(val postId: Long):
        Screen(
            id = 4,
            safeArgs = mapOf("id" to postId.toString()),
            addToBackStack = true
        )
}