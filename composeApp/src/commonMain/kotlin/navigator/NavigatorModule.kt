package navigator

import database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import repositories.UserRepository

val navigatorModule = module {
    factory { CoroutineScope(Dispatchers.IO) }
    factory { UserRepository(get(), get()) }
    single { NavigatorViewModel(get(), get()) }
    single<AppDatabase> {
        AppDatabase(get())
    }
}