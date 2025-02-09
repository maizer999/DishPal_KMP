package repositories

import domain.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PostRepository(private val httpClient: HttpClient) {
    suspend fun getAll(): List<Post> {
        return httpClient.get("https://run.mocky.io/v3/f0b64475-c9c7-4579-a7f1-dcd16b97b947").body()
    }

    suspend fun get(id: Long) : Post {
        return httpClient.get("https://run.mocky.io/v3/f0b64475-c9c7-4579-a7f1-dcd16b97b947").body()
    }
}