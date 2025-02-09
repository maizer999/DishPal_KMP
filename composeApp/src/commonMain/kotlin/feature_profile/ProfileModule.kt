package feature_profile

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import repositories.UserRepository


val profileModule = module {
    factory { CoroutineScope(Dispatchers.Main) }
    factory { UserRepository(get(), get()) }
    single { ProfileViewModel(get(), get()) }
}