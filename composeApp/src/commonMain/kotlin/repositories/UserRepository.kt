package repositories

import database.AppDatabase
import database.getAllUsers
import database.removeAllUsers
import database.saveUser
import domain.User
import io.ktor.client.HttpClient

class UserRepository(
    private val database: AppDatabase,
    private val httpClient: HttpClient) {

    suspend fun getUser() : User? {
        return database.getAllUsers().firstOrNull()
    }

    suspend fun saveUser(user: User) {
        // Always keep one record //
        database.removeAllUsers()
        database.saveUser(user = user)
    }

}