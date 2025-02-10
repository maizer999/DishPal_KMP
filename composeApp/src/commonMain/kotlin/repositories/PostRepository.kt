package repositories

import domain.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PostRepository(private val httpClient: HttpClient) {
    suspend fun getAll(): List<Post> {
        return httpClient.get("https://run.mocky.io/v3/285b46b5-7462-43ff-b331-d15a3b832223").body()
    }

    suspend fun get(id: Long) : Post {
        return httpClient.get("https://run.mocky.io/v3/97d2e461-0307-4dd3-a41c-52ce0b743a1f").body()
    }
}