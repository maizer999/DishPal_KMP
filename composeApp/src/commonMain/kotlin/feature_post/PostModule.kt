package feature_post

import repositories.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module


val postModule = module {
    factory { CoroutineScope(Dispatchers.Main) }
    factory { PostRepository(get()) }
    single { NewPostViewModel() }
    single { PostViewModel(get(), get()) }
}
