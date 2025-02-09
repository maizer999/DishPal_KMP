package database

import domain.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

fun AppDatabase.savePost(scope: CoroutineScope, post: Post): Job {
    return scope.launch {
        val imageUrlsJson = post.dishImageUrl?.let { Json.encodeToString(it) }
        this@savePost.postQueries.insertPost(
            id = post.id,
            createdByUserId = post.createdBy?.id ?: 0L,
            dishImageUrl = imageUrlsJson,
            dishName = post.dishName,
            dishType = post.dishType,
            dishDescription = post.dishDescription,
            recipe = post.recipe,
            isLiked = if (post.isLiked) 1 else 0,
            isDishPal = if (post.isDishPal) 1 else 0,
            localId = null
        )
    }
}

fun parseImageUrlsFromJson(jsonString: String): List<String> {
    return Json.decodeFromString<List<String>>(jsonString)
}

fun db.schema.Post.asPost() : Post {
    return Post(
        id = id ?: 0,
        createdByUserId =  this.createdByUserId,
        dishImageUrl = this.dishImageUrl?.let { parseImageUrlsFromJson(this.dishImageUrl) } ,
        dishName = this.dishName,
        dishType = this.dishType,
        dishDescription = this.dishDescription,
        recipe = this.recipe,
        isLiked = this.isLiked == 1L,
        isDishPal = this.isDishPal == 1L,
        createdBy = null
    )
}

fun AppDatabase.getAllPosts(scope: CoroutineScope, onResult: (List<Post>) -> Unit): Job {
    return scope.launch {
        val postQueryResult = this@getAllPosts.postQueries.selectAllPosts().executeAsList()
        onResult (postQueryResult.map { it.asPost() })
    }
}

fun AppDatabase.getPostById(scope: CoroutineScope, id: Long, onResult: (Post?) -> Unit): Job {
    return scope.launch {
        val postQueryResult = this@getPostById.postQueries.selectPost(id).executeAsOneOrNull()
        onResult(postQueryResult?.asPost())
    }
}
