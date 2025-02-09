package database

import domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

suspend fun AppDatabase.saveUser(user: User) {
    userQueries.insertUser(
        id = user.id,
        rate = user.rate?.toDouble(),
        rateCount = user.rateCount,
        userInitials = user.userInitials,
        userAvatarUrl = user.userAvatarUrl,
        fullName = user.fullName
    )
}

suspend fun AppDatabase.getAllUsers(): List<User> {
    val userQueryResult = userQueries.selectAllUsers().executeAsList()
    return userQueryResult.map {
        User(
            id = it.id,
            rate = it.rate?.toFloat(),
            rateCount = it.rateCount,
            userInitials = it.userInitials,
            userAvatarUrl = it.userAvatarUrl,
            fullName = it.fullName
        )
    }
}

fun AppDatabase.updateUser(scope: CoroutineScope, user: User): Job {
    return scope.launch {
        this@updateUser.userQueries.updateUser(
            rate = user.rate?.toDouble(),
            rateCount = user.rateCount,
            userInitials = user.userInitials,
            fullName = user.fullName ?: "",
            userAvatarUrl = user.userAvatarUrl,
            id = user.id
            )
    }
}

suspend fun AppDatabase.removeAllUsers() {
    this.userQueries.removeAllUsers()
}