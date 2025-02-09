package repositories

import domain.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PostRepository(private val httpClient: HttpClient) {
    suspend fun getAll(): List<Post> {
        return httpClient.get("https://run.mocky.io/v3/97d2e461-0307-4dd3-a41c-52ce0b743a1f").body()
    }

    suspend fun get(id: Long) : Post {
        return httpClient.get("https://run.mocky.io/v3/97d2e461-0307-4dd3-a41c-52ce0b743a1f").body()
    }
}