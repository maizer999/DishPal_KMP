package di

import IOSDriverFactory
import app.cash.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformCoreModule: Module = module {
    single<SqlDriver> {
        IOSDriverFactory().createDriver()
    }
}