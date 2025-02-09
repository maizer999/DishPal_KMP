package feature_home

import platform.LocationClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import repositories.PostRepository

fun homeModule(locationClient: LocationClient) = module {
    factory { CoroutineScope(Dispatchers.Main) }
    factory { PostRepository(get()) }
    single { HomeViewModel(get(), get(), locationClient) }
}
